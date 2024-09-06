package com.mycompany.springframework.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.dto.Ch13UpdateBoardForm;
import com.mycompany.springframework.dto.Ch13WriteBoardForm;
import com.mycompany.springframework.interceptor.LoginCheck;
import com.mycompany.springframework.service.Ch13BoardService;
import com.mycompany.springframework.service.Ch13MemberService;
import com.mycompany.springframework.service.Ch13MemberService.JoinResult;
import com.mycompany.springframework.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch13")
public class Ch13Controller {
	@Autowired
	private Ch13BoardService boardService;
	
	@Resource
	private Ch13MemberService memberService;
	
	@LoginCheck
	@GetMapping("/writeBoardForm") // 게시물 쓰기 폼 요청
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/writeBoardForm";
	}
	
//	@PostMapping("/writeBoard")
//	public String writeBoard(Ch13WriteBoardForm form) throws Exception {
//		Ch13Board board = new Ch13Board();
//		board.setBtitle(form.getBtitle());
//		board.setBcontent(form.getBcontent());
//		board.setMid("user");
//		MultipartFile battach = form.getBattach();
//		if (!battach.isEmpty()) {
//			board.setBattachoname(battach.getOriginalFilename());
//			board.setBattachtype(battach.getContentType());
//			
//			String filename = new Date().getTime() + "-" + battach.getOriginalFilename();
//			board.setBattachsname(filename);
//			
//			String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
//			File file = new File(saveDir, filename);
//			battach.transferTo(file);
//		}
//		boardService.writeBoard(board);
//		return "redirect:/";
//	}
	
	// DB에 파일 넣기
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form, HttpSession session) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		Ch13Member member = (Ch13Member) session.getAttribute("login");
		board.setMid(member.getMid());
		MultipartFile battach = form.getBattach();
		if (!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			board.setBattachdata(battach.getBytes()); // 파일 내용의 바이트 배열을 반환
		}
		boardService.writeBoard(board);
		return "redirect:/ch13/boardList";
	}
	
	@LoginCheck
	@GetMapping("/boardList")
	public String boardList(
			Model model, 
			@RequestParam(defaultValue="1") int pageNo,
			HttpSession session
		) {
		model.addAttribute("chNum", "ch13");	
		int totalRows = boardService.getTotalRows(); // 데이터 베이스에서 카운팅해서 가져와야 함
		// pageNo - 사용자가 클릭한 번호를 브라우저에서 받아야 함
		Ch13Pager pager = new Ch13Pager(7, 5, totalRows, pageNo);
		session.setAttribute("pager", pager); // model 저장은 request 범위 내에 있음
		
		List<Ch13Board> list = boardService.getBoardList(pager);
		model.addAttribute("list", list); // boardList.jsp에서 사용하기 위함
		return "ch13/boardList";
	}
	
	@GetMapping("/detailBoardAddHitcount")
	public String detailBoardAddHitcount(int bno, Model model) {
		boardService.addHitCount(bno);
		return detailBoard(bno, model);
	}
	
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno); // bno에 해당하는 게시물의 정보가 들어가 있음
		model.addAttribute("chNum", "ch13");
		model.addAttribute("board", board);
		return "ch13/detailBoard";
	}
	
	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception {
		Ch13Board board = boardService.getBoardAttach(bno);
		
		// 응답 헤더에 들어가는 contentType 내용
		String contentType = board.getBattachtype();
		response.setContentType(contentType);
		
		// 파일로 저장하기 위한 설정
		// fileName에 한글 인식 시키기
		String fileName = board.getBattachoname();
		String encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");		

		// 응답 본문에 파일 데이터를 출력
		OutputStream out = response.getOutputStream();
		out.write(board.getBattachdata());
		out.flush();
		out.close();
	}
	
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Ch13UpdateBoardForm form) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBno(form.getBno());
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		MultipartFile mf = form.getBattach();
		if (!mf.isEmpty()) {
			board.setBattachoname(mf.getOriginalFilename());
			board.setBattachtype(mf.getContentType());
			board.setBattachdata(mf.getBytes());
		}
		boardService.updateBoard(board);
		return "redirect:/ch13/detailBoard?bno=" + form.getBno();
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno, HttpSession session) {
		boardService.deleteBoard(bno);
		Ch13Pager pager = (Ch13Pager) session.getAttribute("pager");
		int pageNo = pager.getPageNo();
		return "redirect:/ch13/boardList?pageNo=" + pageNo;
	}
	
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch13Member member, Model model) {
		member.setMenabled(true);
		log.info(member.toString());
		JoinResult joinresult = memberService.join(member);
		if (joinresult == JoinResult.FAIL_DUPLICATED_MID) {
			String errorMessage = "아이디가 존재합니다.";
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("chNum", "ch13");
			return "ch13/joinForm";
		} else { // JoinResult.SUCCESS인 경우
			return "redirect:/ch13/loginForm";
		}
	}
	
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/loginForm";
	}
	
	@PostMapping("/login")
	public String login(Ch13Member member, Model model, HttpSession session) {
		LoginResult loginResult = memberService.login(member);
		if (loginResult == LoginResult.FAIL_MID) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMid", "아이디가 존재하지 않습니다.");
			return "ch13/loginForm";
		} else if (loginResult == LoginResult.FAIL_MPASSWORD) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMpassword", "비밀번호가 맞지 않습니다.");
			return "ch13/loginForm";
		} else if (loginResult == LoginResult.FAIL_ENABLED) {
			return "redirect:/";
		} else { // LoginResult.SUCCESS 일 경우
			session.setAttribute("login", member);
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// session api같은 servlet api는 컨트롤러에서만 쓰고 서비스로 넘기면 안된다.
		session.removeAttribute("login");
		return "redirect:/ch13/loginForm";
	}
}