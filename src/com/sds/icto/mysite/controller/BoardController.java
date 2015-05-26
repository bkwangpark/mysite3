package com.sds.icto.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> list = boardService.fetchList();
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping(value = { "/write" }, method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo vo) {
		vo.setView_cnt((long) 0);
		boardService.insert(vo);
		return "redirect:/board";
	}

	@RequestMapping(value = { "/modify/{no}/{authMember_no}" }, method = RequestMethod.GET)
	public String modifyForm(@PathVariable Long no,
			@PathVariable Long authMember_no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("authMember_no", authMember_no);
		BoardVo vo = boardService.showContent(no);
		model.addAttribute("vo", vo);
		return "board/modify";
	}

	@RequestMapping(value = { "/modify" }, method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo, Model model) {
		boardService.updateContent(vo);
		BoardVo vo1 = boardService.showContent(vo.getNo());
		model.addAttribute("vo1", vo1);
		return "board/view";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String show(@PathVariable Long no, Model model) {
		model.addAttribute("no", no);
		BoardVo vo = boardService.showContent(no);
		System.out.println(vo.getView_cnt());
		boardService.view_cntUpdate(vo);
		System.out.println(vo.getView_cnt());
		model.addAttribute("vo1", vo);
		return "board/view";
	}

	@RequestMapping(value = { "/delete/{no}/{mem_no}" }, method = RequestMethod.GET)
	public String delete(@ModelAttribute BoardVo vo) {
		boardService.deleteContent(vo);
		return "redirect:/board";
	}

	@RequestMapping(value = { "/find" }, method = RequestMethod.POST)
	public String findlist(@RequestParam String key, Model model) {
		List<BoardVo> findlist = boardService.find(key);
		model.addAttribute("list", findlist);
		return "board/list";
	}

}
