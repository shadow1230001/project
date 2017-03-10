package com.intransition.labs.controller;

import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.service.ChapterService;
import com.intransition.labs.service.CreativeService;
import com.intransition.labs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private TagService tagService;

    @Autowired
    private CreativeService creativeService;

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/")
    public String index(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("tags", tagService.getAllTags());

        Page<Creative> creatives = creativeService.findAllByOrderByCreatedDesc(pageable);

        model.addAttribute("creatives", creatives);

        return "index";
    }


    @RequestMapping("/freshbar")
    public String freshBar(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("tags", tagService.getAllTags());

        Page<Creative> creatives = creativeService.findAllByOrderByEditedDesc(pageable);

        model.addAttribute("creatives", creatives);

        return "freshbar";
    }


    @GetMapping("/creative/")
    public String viewEmptyCreative() {
        return "redirect:/";
    }

    @RequestMapping(value = "/creative/{id}")
    public String viewCreative(@PathVariable(value = "id", required = true) int id, Model model) {
        Creative creative = creativeService.getCreative(id);

        if (creative == null) return "creative/creativeNotFound";

        creative.setViewed(creative.getViewed() + 1);

        model.addAttribute("creative", creative);
        model.addAttribute("chapters", chapterService.sortChaptersByOrder(creative.getChapters()));

        return "creative/creative";
    }

}