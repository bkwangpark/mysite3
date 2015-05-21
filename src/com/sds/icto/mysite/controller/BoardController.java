package com.sds.icto.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value ={ "", "/", "/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> list = boardService.fetchList();
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping(value = { "/write" }, method = RequestMethod.GET)
	public String write(@ModelAttribute BoardVo vo) {
		return "board/write";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String show(@PathVariable Long no,Model model) {
		model.addAttribute("no", no);
		BoardVo vo=	boardService.showContent(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String deleteForm(@PathVariable Long no, Model model){
		model.addAttribute("no", no);
		return "board/deleteform";
	}

	@RequestMapping(value = {"/delete" }, method = RequestMethod.POST)
	public String delete(@ModelAttribute BoardVo vo) {
		boardService.deleteContent(vo);
		return "redirect:/board";
	}
}
