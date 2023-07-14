package com.goodee.mvcBoard;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class BoardListener implements HttpSessionListener {

    public BoardListener() {
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    }
	
}
