package com.javachan.web;

import com.javachan.domain.Message;
import com.javachan.domain.Thread;
import com.javachan.service.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "thread/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute Thread thread, BindingResult bindingResult, Model model,
                        RedirectAttributes redirectAttrs) {
        if(bindingResult.hasErrors()) {
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.thread", bindingResult);
            redirectAttrs.addFlashAttribute("thread", thread);
            return String.format("redirect:/_/%s/", thread.getBoard().getSlug());
        }

        threadRepository.save(thread);
        redirectAttrs.addFlashAttribute("postSuccess", true);
        return String.format("redirect:/thread/%d/", thread.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model) {
        Thread thread = threadRepository.findOne(id);
        model.addAttribute("thread", thread);

        if (!model.containsAttribute("newReply")) {
            model.addAttribute("newReply", new Message());
        }

        return "thread/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute Message newReply, BindingResult bindingResult,
                       RedirectAttributes redirectAttrs) {
        if(bindingResult.hasErrors()) {
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.newReply", bindingResult);
            redirectAttrs.addFlashAttribute("newReply", newReply);
        } else {
            Thread thread = threadRepository.findOne(id);
            thread.addReply(newReply);
            threadRepository.save(thread);
            redirectAttrs.addFlashAttribute("postSuccess", true);
        }

        return String.format("redirect:/thread/%d/", id);
    }

}
