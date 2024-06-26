package io.junrock.GAZA.domain.image.repository;

import io.junrock.GAZA.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findAllByLocationType(String locationType);

    Image findByLocationType(String locationType);
}
