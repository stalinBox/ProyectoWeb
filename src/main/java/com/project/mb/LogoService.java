package com.project.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.project.utils.Logos;

@ManagedBean(name = "LogoService", eager = true)
@ApplicationScoped
public class LogoService {
	private List<Logos> Logos;

	@PostConstruct
	public void init() {
		Logos = new ArrayList<Logos>();
		Logos.add(new Logos(0, "Logo 1", "/images/contentFlowFaps/1.png"));
		Logos.add(new Logos(1, "Logo 2", "/images/contentFlowFaps/2.png"));
		Logos.add(new Logos(2, "Logo 3", "/images/contentFlowFaps/3.png"));
		Logos.add(new Logos(3, "Logo 4", "/images/contentFlowFaps/4.png"));
		Logos.add(new Logos(4, "Logo 5", "/images/contentFlowFaps/5.png"));
		Logos.add(new Logos(5, "Logo 6", "/images/contentFlowFaps/6.png"));
		Logos.add(new Logos(6, "Logo 7", "/images/contentFlowFaps/7.png"));
		Logos.add(new Logos(7, "Logo 8", "/images/contentFlowFaps/8.png"));
		Logos.add(new Logos(8, "Logo 9", "/images/contentFlowFaps/9.png"));
		Logos.add(new Logos(9, "Logo 10", "/images/contentFlowFaps/10.png"));
		Logos.add(new Logos(10, "Logo 11", "/images/contentFlowFaps/11.png"));
		Logos.add(new Logos(11, "Logo 12", "/images/contentFlowFaps/12.png"));
		Logos.add(new Logos(12, "Logo 13", "/images/contentFlowFaps/13.png"));
		Logos.add(new Logos(13, "Logo 14", "/images/contentFlowFaps/14.png"));
		Logos.add(new Logos(14, "Logo 15", "/images/contentFlowFaps/15.png"));
		Logos.add(new Logos(15, "Logo 16", "/images/contentFlowFaps/16.png"));
		Logos.add(new Logos(16, "Logo 17", "/images/contentFlowFaps/17.png"));
		Logos.add(new Logos(17, "Logo 18", "/images/contentFlowFaps/18.png"));
		Logos.add(new Logos(18, "Logo 19", "/images/contentFlowFaps/19.png"));
	}

	public List<Logos> getLogos() {
		return Logos;
	}
}
