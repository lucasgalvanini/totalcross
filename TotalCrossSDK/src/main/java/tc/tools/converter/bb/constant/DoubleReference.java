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

package tc.tools.converter.bb.constant;

import tc.tools.converter.bb.JavaClass;
import tc.tools.converter.bb.JavaConstant;
import totalcross.io.DataStream;
import totalcross.io.IOException;

public class DoubleReference implements ConstantInfo {
  private JavaClass jclass;

  public JavaConstant value1;
  public JavaConstant value2;

  public DoubleReference(JavaClass jclass) {
    this.jclass = jclass;
  }

  @Override
  public java.lang.String toString() {
    return "[" + value1.toString() + ", " + value2.toString() + "]";
  }

  @Override
  public int length() {
    return 4;
  }

  @Override
  public void load(DataStream ds) throws IOException {
    value1 = jclass.getConstant(ds.readUnsignedShort(), this);
    value2 = jclass.getConstant(ds.readUnsignedShort(), this);
  }

  @Override
  public void save(DataStream ds) throws IOException {
    ds.writeShort(jclass.getConstantIndex(value1, this));
    ds.writeShort(jclass.getConstantIndex(value2, this));
  }
}
