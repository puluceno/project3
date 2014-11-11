package edu.luc.comp433.model;

import java.io.Serializable;

public interface BaseEntity<I extends Number> extends Serializable  {

	I getId();
	void setId(I id);
	
}

