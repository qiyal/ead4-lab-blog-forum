package kz.blog.spring.forumapi.repository;

import kz.blog.spring.forumapi.model.ForumMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumMemberRepository extends JpaRepository<ForumMember, Long> {
}
