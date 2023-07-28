package com.kyoofus.framework.core.util;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

public class UlidGenerator {
  public static Ulid getUlid(){ 
    // https://github.com/f4b6a3/ulid-creator
    return UlidCreator.getUlid();
  }
}///~
