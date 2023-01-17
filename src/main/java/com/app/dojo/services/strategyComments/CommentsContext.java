package com.app.dojo.services.strategyComments;

import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentsContext {

    private CommentsStrategy commentsStrategy;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CommentRepository commentRepository;

    public CommentsStrategy loadStrategy(String modelCondition){
        switch (modelCondition){
            default:
                return new Comments(this.commentRepository);
        }
    }
}
