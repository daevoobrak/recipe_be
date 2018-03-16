package com.concretepage.rating.service;

import java.util.List;

import com.concretepage.recipe.entity.Recipe;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface IRatingService {
	boolean rate(ObjectNode rating, ObjectNode response);
}
