/*********************************************************************************
 *  TotalCross Software Development Kit                                          *
 *  Copyright (C) 2000-2012 SuperWaba Ltda.                                      *
 *  Copyright (C) 2012-2020 TotalCross Global Mobile Platform Ltda.              *
 *  All Rights Reserved                                                          *
 *                                                                               *
 *  This library and virtual machine is distributed in the hope that it will     *
 *  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                         *
 *                                                                               *
 *  This file is covered by the GNU LESSER GENERAL PUBLIC LICENSE VERSION 2.1    *
 *  A copy of this license is located in file license.txt at the root of this    *
 *  SDK or can be downloaded here:                                               *
 *  http://www.gnu.org/licenses/lgpl-2.1.txt                                     *
 *                                                                               *
 *********************************************************************************/

package tc.tools.converter.tclass;

import totalcross.io.DataStreamLE;

/* Represents an Exception (try/catch) declared in the code */
public final class TCException {
  // The class name to whom this Exception belongs to
  public String className;
  // The starting program counter
  public int /*uint16*/ startPC;
  // The ending program counter
  public int /*uint16*/ endPC;
  // The program counter of the code that handles this exception
  public int /*uint16*/ handlerPC;
  // The regO that stores the instance of this Exception
  public int /*uint16*/ regO;

  public void write(DataStreamLE ds) throws totalcross.io.IOException {
    ds.writeShort(startPC);
    ds.writeShort(endPC);
    ds.writeShort(handlerPC);
    ds.writeShort(regO);
    ds.writeShort(tc.tools.converter.GlobalConstantPool.getClassIndex(className));
    if (tc.tools.converter.J2TC.dump) {
      System.out.println(className + ": " + startPC + "-" + endPC + " handled @ " + handlerPC + ", obj @ " + regO);
    }
  }
}
