package com.steamlfg.controller;

import com.steamlfg.model.dto.CommentDTO;
import com.steamlfg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    RedirectView addComment(@RequestParam Map<String, String> query) throws IOException {
        CommentDTO commentDTO = commentService.addComment(query.get("msg"), Integer.parseInt(query.get("announcementHash")));
        return new RedirectView("/announcements/"+commentDTO.getAnnouncementByAnnouncementId().getAnnouncementHash());
    }

    @GetMapping("/page/{id}")
    List<CommentDTO> getLastCommentByPage(@PathVariable Integer id, @RequestParam int announcementHash) {
        if (id < 0)
            return null;
        else return commentService.findAllByAnnouncementHashOrderByMessageDateTimeDesc(id,announcementHash);
    }
}
