package com.duyngoc.repository;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.ImageUrl;
import java.lang.Long;
import java.util.List;

public interface ImageUrlRepostiory extends CrudRepository<ImageUrl, Long> {

	List<ImageUrl> findByApartmentId(String aparmentid);
}
