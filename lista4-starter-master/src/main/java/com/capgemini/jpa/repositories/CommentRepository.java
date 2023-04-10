package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
