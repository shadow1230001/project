package com.intransition.labs.editor;

import com.intransition.labs.domain.content.Chapter;
import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.domain.content.Tag;
import com.intransition.labs.repository.CreativeRepository;
import com.intransition.labs.repository.TagRepository;
import com.intransition.labs.service.AwardService;
import com.intransition.labs.service.AwardType;
import com.intransition.labs.service.MessageByLocaleService;
import com.intransition.labs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AwardService awardService;

    @Autowired
    private MessageByLocaleService messageByLocaleService;


    @Override
    public Creative convertFromJsonToJpaCreative(JsonCreative jsonCreative) {
        Creative creative = new Creative();

        creative.setAuthor(securityService.getLoggedInUser());


        creative.setChapters(convertJsonChaptersToJpa(creative, jsonCreative.getChapters()));

        creative.setTags(convertJsonTagsToJpa(creative, jsonCreative.getTags()));

        Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

        creative.setCreated(timestamp);
        creative.setEdited(timestamp);

        creative.setDescription(jsonCreative.getDescription());
        creative.setName(jsonCreative.getName());
        creative.setRating(0);
        creative.setViewed(0);

        return creative;
    }

    private Set<Tag> convertJsonTagsToJpa(Creative parentCreative, Set<JsonTag> tags) {
        if ((tags == null) || tags.isEmpty()) return new HashSet<Tag>();

        Set<Tag> convertedTags = new HashSet<Tag>();

        for (JsonTag jsonTag : tags) {
            List<Tag> t = tagRepository.findByName(jsonTag.getText());
            if (!t.isEmpty()) {
                t.get(0).getCreatives().add(parentCreative);
                continue;
            }

            Tag convertedTag = new Tag();
            convertedTag.setAddress(jsonTag.getText());
            convertedTag.setName(jsonTag.getText());
            convertedTags.add(convertedTag);
        }

        return convertedTags;
    }

    private Set<Tag> convertJsonTagsToJpa(Chapter chapter, Set<JsonTag> tags) {
        if ((tags == null) || tags.isEmpty()) return new HashSet<Tag>();

        Set<Tag> convertedTags = new HashSet<Tag>();

        for (JsonTag jsonTag : tags) {
            List<Tag> t = tagRepository.findByName(jsonTag.getText());
            if (!t.isEmpty()) {
                t.get(0).getChapters().add(chapter);
                continue;
            }

            Tag convertedTag = new Tag();
            convertedTag.setAddress(jsonTag.getText());
            convertedTag.setName(jsonTag.getText());
            convertedTags.add(convertedTag);
        }

        return convertedTags;
    }

    private Set<Chapter> convertJsonChaptersToJpa(Creative parentCreative, Set<JsonChapter> chapters) {
        Set<Chapter> convertedChapters = new HashSet<Chapter>();

        for (JsonChapter chapter : chapters) {
            Chapter convertedChapter = new Chapter();

            convertedChapter.setChapterOrder(chapter.getId());
            convertedChapter.setContent(chapter.getContent());
            convertedChapter.setParentCreative(parentCreative);
            convertedChapter.setTitle(chapter.getTitle());
            convertedChapter.setTags(convertJsonTagsToJpa(convertedChapter, chapter.getTags()));

            convertedChapters.add(convertedChapter);
        }

        return convertedChapters;
    }

    @Override
    public Alert validate(Creative creative) {
        Alert alert = new Alert();
        alert.setLevel(AlertLevel.SUCCESS);
        alert.setTitle("Succces!");
        alert.setDescription("Your creative has created. Maybe You want to make one more?");

        if (creative.getName() == null || creative.getName().isEmpty()) {
            addError(alert, messageByLocaleService.getMessage("page.editor.error.creativename.empty"));
        } else if (creative.getName().length() > 100) {
            addError(alert, messageByLocaleService.getMessage("page.editor.error.creativename.toobig", new Integer[]{100}));
        }

        if (creative.getDescription() == null || creative.getDescription().isEmpty()) {
            addError(alert, "Creative description is epmty");
        } else if (creative.getDescription().length() > 400) {
            addError(alert, messageByLocaleService.getMessage("page.editor.error.description.toobig", new Integer[]{400}));
        }
        /*
		if( creative.getTags().size() < 2  ) {
			addError( alert, "Creative must have at least two tags" );
		}*/

        if (creative.getChapters().isEmpty()) {
            addError(alert, messageByLocaleService.getMessage("page.editor.error.nochapters"));
        }

        for (Chapter ch : creative.getChapters()) {
            if (ch.getContent() == null || ch.getContent().isEmpty()) {
                addError(alert, messageByLocaleService.getMessage("page.editor.error.chapter.nocontent"));
            }
			/*
			if( ch.getTitle() == null || ch.getTitle().isEmpty() ) {
				addError( alert, messageByLocaleService.getMessage( "page.editor.error.chapter.title.empty" ) );
			}*/
			/*
			if( ch.getTags().size() < 2 ) {
				for( Tag t : ch.getTags() ) 
				Logger.getAnonymousLogger().warning( t.getName() );
				addError( alert, "The chapter #" + ch.getChapterOrder() + " must have at least two tags" );
			}*/
        }
		
		/*
		if( !alert.hasErrors() ) {
			for( Tag t : tags ) {
				 creative.getTags();
			}
			tagRepository.saveAndFlush();
		}
		*/

        return alert;
    }

    private void addError(Alert alert, String message) {
        alert.setLevel(AlertLevel.DANGER);
        alert.setTitle("Oh snap!");
        alert.setDescription("Something went wrong.");
        alert.getMessages().add(message);
        alert.hasErrors = true;
    }

    @Override
    public void saveAndFlush(Creative creative) {
        switch (securityService.getLoggedInUser().getCreatives().size()) {
            case 4:
                awardService.giveUserAward(securityService.getLoggedInUser(), AwardType.FIFTH_CREATIVE);
                break;
            case 9:
                awardService.giveUserAward(securityService.getLoggedInUser(), AwardType.TENTH_CREATIVE);
                break;
        }

        creativeRepository.save(creative);
    }


}
