package com.kt.ipmc.check.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kt.ipmc.check.common.HttpUtil;
import com.kt.ipmc.check.domain.ChkHistVO;
import com.kt.ipmc.check.domain.ChkSvFsVO;
import com.kt.ipmc.check.service.ChkHistService;

public class CheckController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ChkHistService service = ChkHistService.getInstance();
		
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		String chkUserId = (String) session.getAttribute("userid");
		String chkServiceId = (String) session.getAttribute("serviceid");
		String chkComment = request.getParameter("comment");
		Timestamp chkDate = Timestamp.valueOf(LocalDateTime.now());
		
		ChkHistVO chkHist = new ChkHistVO();
		chkHist.setChkUserId(chkUserId);
		chkHist.setChkServiceId(chkServiceId);
		chkHist.setChkComment(chkComment);
		chkHist.setChkDate(chkDate);
		chkHist.setChkSvFsList(this.getChkSvFsList(request));
		
		if (action == null || action.equals("check")) {
			if (service.countTodayChkHist() == 0) {
				HttpUtil.forward(request, response, "/check/status.jsp");
			} else {
				HttpUtil.forward(request, response, "/check.do?action=list");
			}
		} else if (action.equals("list")) {
			request.setAttribute("list", service.selectChkHist());
			HttpUtil.forward(request, response, "/check/list.jsp");
		} else if (action.equals("insertCheck")) {
			service.insertChkHist(chkHist);
			HttpUtil.forward(request, response, "/check/list.jsp");
		} 
	}
	
	private ArrayList<ChkSvFsVO> getChkSvFsList(HttpServletRequest request) {
		ArrayList<ChkSvFsVO> list = new ArrayList<ChkSvFsVO>();
		ChkSvFsVO chkSvFs = null;
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("1");
		chkSvFs.setChkResult(request.getParameter("fs_sys1"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("2");
		chkSvFs.setChkResult(request.getParameter("fs_sys2"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("3");
		chkSvFs.setChkResult(request.getParameter("fs_sys3"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("4");
		chkSvFs.setChkResult(request.getParameter("fs_sys4"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("5");
		chkSvFs.setChkResult(request.getParameter("fs_sys5"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("6");
		chkSvFs.setChkResult(request.getParameter("fs_sys6"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("7");
		chkSvFs.setChkResult(request.getParameter("fs_sys7"));
		list.add(chkSvFs);
		
		chkSvFs = new ChkSvFsVO();
		chkSvFs.setSystemId("8");
		chkSvFs.setChkResult(request.getParameter("fs_sys8"));
		list.add(chkSvFs);
		
		return list;
	}
}
