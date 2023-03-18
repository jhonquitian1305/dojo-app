package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.CommentResponseBuilder;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CommentService;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import com.app.dojo.services.strategyComments.CommentsContext;
import com.app.dojo.services.strategyComments.CommentsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MapperComment mapperComment;

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    CommentsContext commentsContext;

    @Override
    public Comment createOne(CommentDTO commentDTO) throws Exception {
        Course courseFound = this.courseService.getOne(commentDTO.getCourse());
        Teacher teacherFound = this.teacherService.getById(commentDTO.getTeacher());
        Student studentFound = this.studentService.getStudentById(commentDTO.getStudent());

        Comment commentCreated = this.commentRepository.save(this.mapperComment.createComment(commentDTO, courseFound, teacherFound, studentFound));

        return commentCreated;
    }

    @Override
    public CommentResponse getAllByCondition(int numberPage, int pageSize, String sortBy, String sortDir, String model, Long idCondition) throws Exception {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(numberPage, pageSize, sort);

        CommentsStrategy commentsStrategy = this.commentsContext.loadStrategy(model.toUpperCase());
        Page<Comment> commentsFound = commentsStrategy.findComments(pageable, idCondition);

        return new CommentResponseBuilder()
                .setContent(commentsFound.getContent())
                .setNumberPage(commentsFound.getNumber())
                .setSizePage(commentsFound.getSize())
                .setTotalElements(commentsFound.getTotalElements())
                .setTotalPages(commentsFound.getTotalPages())
                .setLastOne(commentsFound.isLast())
                .build();
    }

    @Override
    public Comment getById(Long id) {
        Optional<Comment> commentFound = this.commentRepository.findById(id);
        if(commentFound.isEmpty()){
            throw new NotFoundException(String.format("Comment with id %s doesn't exists", id));
        }
        return commentFound.get();
    }

    @Override
    public Comment updateOne(Long id, CommentDTO commentDTO) throws Exception {
        Comment commentFound = this.getById(id);
        Course courseFound = this.courseService.getOne(commentDTO.getCourse());
        Teacher teacherFound = this.teacherService.getById(commentDTO.getTeacher());
        Student studentFound = this.studentService.getStudentById(commentDTO.getStudent());

        Comment commentUpdate = this.mapperComment.updateComment(commentDTO, commentFound, courseFound, teacherFound, studentFound);

        return this.commentRepository.save(commentUpdate);
    }

    @Override
    public void deleteOne(Long id) {
        this.getById(id);
        this.commentRepository.deleteById(id);
    }
}
