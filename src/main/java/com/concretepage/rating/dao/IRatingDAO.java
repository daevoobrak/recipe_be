package com.concretepage.rating.dao;
import java.util.List;

import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.IBaseDAO;

public interface IRatingDAO extends IBaseDAO<Rating> {

	Rating getRatingByIpandId(Long id, String ip);
    
}
 