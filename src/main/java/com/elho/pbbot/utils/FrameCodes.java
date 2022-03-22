package com.elho.pbbot.utils;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import onebot.OnebotFrame;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class FrameCodes implements MessageCodec<OnebotFrame.Frame, OnebotFrame.Frame> {
   @Override
   public void encodeToWire(Buffer buffer, OnebotFrame.Frame frame) {
   }

   @Override
   public OnebotFrame.Frame decodeFromWire(int pos, Buffer buffer) {
      return null;
   }

   @Override
   public OnebotFrame.Frame transform(OnebotFrame.Frame frame) {
      return frame;
   }

   @Override
   public String name() {
      return FrameCodes.class.getSimpleName();
   }

   @Override
   public byte systemCodecID() {
      return -1;
   }
}
