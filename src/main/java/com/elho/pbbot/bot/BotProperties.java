package com.elho.pbbot.bot;

/**
 * @author zyf
 * @Date 2022-03-15
 */
public abstract class BotProperties {

  public static final String URL = "/ws/*/";
  public static final Integer MAXTEXTMESSAGEBUFFERSIZE = 512000;
  public static final Integer MAXBINARYMESSAGEBUFFERSIZE = 512000;
  public static final Long MAXSESSIONIDLETIMEOUT = 15 * 60000L;
  public static final Long APITIMEOUT = 60000L;

}
