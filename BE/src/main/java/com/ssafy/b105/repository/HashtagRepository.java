package com.ssafy.b105.repository;

<<<<<<< HEAD
import com.ssafy.b105.entity.Hashtag;
=======
import com.ssafy.b105.entity.campaign.Hashtag;
>>>>>>> dev
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByName(String tagName);

}
