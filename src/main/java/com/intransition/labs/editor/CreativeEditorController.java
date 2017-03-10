package com.intransition.labs.editor;

import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.repository.CreativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreativeEditorController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private CreativeRepository creativeRepository;

    @GetMapping("/personal/editor")
    public String editorPage() {
        return "personal/editor";
    }

    @RequestMapping(value = "/personal/editor/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Alert verifyAndPostCreative(@RequestBody JsonCreative jsonCreative) {
        Creative creative = editorService.convertFromJsonToJpaCreative(jsonCreative);
        creativeRepository.save(creative);

        Alert alert = editorService.validate(creative);

        if (alert.getLevel() == AlertLevel.SUCCESS) {
            editorService.saveAndFlush(creative);
        }
        return alert;
    }

}
