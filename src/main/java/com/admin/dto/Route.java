package com.admin.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int routeId;
	private String source;
	private String destination;
	private float distance;
	private float duration;
}
