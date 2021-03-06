package com.intransition.labs.controller;

import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.domain.content.Tag;
import com.intransition.labs.service.CreativeService;
import com.intransition.labs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class TagsController {

    @Autowired
    private TagService tagService;

    @Autowired
    private CreativeService creativeService;

    @RequestMapping(value = "/tags/{tag}", method = RequestMethod.GET)
    public String searchByTag(@PageableDefault(size = 5) Pageable pageable, @PathVariable(value = "tag") String tag, Model model) {
        List<Creative> creatives = creativeService.findAllByOrderByCreatedDesc(pageable).getContent().parallelStream().filter(creative -> {
            return containsTag(creative.getTags(), tag);
        }).collect(Collectors.toList());
        model.addAttribute("creatives", creatives);

        return "searchbytag";
    }

    private boolean containsTag(Set<Tag> tags, String tag) {
        for (Tag tg : tags) {
            if (tg.getName().equalsIgnoreCase(tag)) return true;
        }
        return false;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> autocomplete(@RequestBody String tag) {
        return tagService.autocomplete(tag);
    }


}
