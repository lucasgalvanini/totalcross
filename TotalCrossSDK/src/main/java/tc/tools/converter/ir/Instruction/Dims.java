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

package tc.tools.converter.ir.Instruction;

import tc.tools.converter.tclass.TCCode;
import totalcross.util.Vector;

public class Dims extends Params {
  public Dims(int line, int d1, int d2, int d3, int d4) {
    super(line, d1, d2, d3, d4);
  }

  public Dims(int line) {
    super(line);
  }

  @Override
  public void toTCCode(Vector vcode) {
    TCCode tc = new TCCode(line);
    tc.len = 0;
    tc.dims__dim1(typeOfParam1 != type_Constant ? param1 : param1 + 65);
    tc.dims__dim2(typeOfParam2 != type_Constant ? param2 : param2 + 65);
    tc.dims__dim3(typeOfParam3 != type_Constant ? param3 : param3 + 65);
    tc.dims__dim4(typeOfParam4 != type_Constant ? param4 : param4 + 65);
    vcode.addElement(tc);
  }
}
