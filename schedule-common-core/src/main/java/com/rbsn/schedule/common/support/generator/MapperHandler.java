package com.rbsn.schedule.common.support.generator;


import com.rbsn.schedule.common.util.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */
public class MapperHandler {
    public String sqlHandler(List<ColumnInfo> cloumsList) {
        StringBuilder baseColumn = new StringBuilder();
        for (ColumnInfo columnInfo : cloumsList) {
            String column = columnInfo.getName();
            String lowerCloum = StringUtil.toLowerCaseFirstOne(StringUtil.lineToHump(column));
            String sLowerCloum = "";
            String sql = "";
            if (lowerCloum.substring(0, 2).equals("is")) {
                sLowerCloum = StringUtil.toLowerCaseFirstOne(lowerCloum.substring(2));
                sql = "a." + column + " AS " + "\"" + sLowerCloum + "\"";
            } else {
                sql = "a." + column + " AS " + "\"" + lowerCloum + "\"";
            }
            baseColumn.append(sql).append(",\r\n").append("        ");
        }
        return baseColumn.substring(0, baseColumn.length() - 11);
    }

    public StringBuilder insertHandler(List<ColumnInfo> cloumsList, String table) {
        StringBuilder insertBefore = new StringBuilder();
        StringBuilder insertAfter = new StringBuilder();
        StringBuilder insert = new StringBuilder();
        String i = "INSERT INTO " + table + "(\n";
        insertBefore.append(i).append("        ");
        int count = 0;
        for (ColumnInfo columnInfo : cloumsList) {
            String a = "";
            String b = "";
            String c = "";
            String d = "";
            String sLowerCloum = "";
            String column = columnInfo.getName();
            String lowerCloum = StringUtil.toLowerCaseFirstOne(StringUtil.lineToHump(column));
            if (lowerCloum.substring(0, 2).equals("is")) {
                sLowerCloum = StringUtil.toLowerCaseFirstOne(lowerCloum.substring(2));
                a = "<if test=\"" + sLowerCloum + " != null\">";
                b = column;
                c = "<if test=\"" + sLowerCloum + " != null\">";
                d = "#{" + sLowerCloum + "}";
            } else {
                a = "<if test=\"" + lowerCloum + " != null\">";
                b = column;
                c = "<if test=\"" + lowerCloum + " != null\">";
                d = "#{" + lowerCloum + "}";
            }

            if (count >= 0 && count < cloumsList.size() - 1) {
                insertBefore.append(a).append("\r\n").append("            ").append(b).append(",\r\n").append("        ")
                        .append("</if>\r\n").append("        ");

                insertAfter.append(c).append("\r\n").append("            ").append(d).append(",\r\n").append("        ")
                        .append("</if>\r\n").append("        ");
            } else if (count == cloumsList.size() - 1) {
                insertBefore.append(a).append("\r\n").append("            ").append(b).append("\r\n").append("        ")
                        .append("</if>").append("        ");

                insertAfter.append(c).append("\r\n").append("            ").append(d).append("\r\n").append("        ")
                        .append("</if>").append("        ");
            }
            count++;
        }
        return insert.append(insertBefore.substring(0, insertBefore.length())).append("\n")
                .append("        ) VALUES (").append("\r\n").append("        ")
                .append(insertAfter.substring(0, insertAfter.length())).append("\r\n").append("    ").append(")");
    }

    public StringBuilder updateHandler(List<ColumnInfo> cloumsList, String table) {
        int count = 0;
        String b = "";
        String sLowerCloum = "";
        String u = "UPDATE " + table + " \n        ";
        StringBuilder update = new StringBuilder();
        update.append(u);
        update.append("<set>\r\n").append("            ");
        for (ColumnInfo columnInfo : cloumsList) {
            String a = "";
            String c = "";
            String column = columnInfo.getName();
            String lowerCloum = StringUtil.toLowerCaseFirstOne(StringUtil.lineToHump(column));
            if (count > 0 && count < cloumsList.size() - 2) {
                if (!lowerCloum.equals("createDate") && !lowerCloum.equals("delFlag") && !lowerCloum.equals("createBy")) {
                    if (lowerCloum.substring(0, 2).equals("is")) {
                        sLowerCloum = StringUtil.toLowerCaseFirstOne(lowerCloum.substring(2));
                        a = "<if test=\"" + sLowerCloum + " != null\">";
                        update.append(a).append("\r\n").append("                ");
                        c = column + " = " + "#{" + sLowerCloum + "}";
                        update.append(c).append(",\r\n").append("            ");
                        update.append("</if>").append("\r\n").append("            ");
                    } else {
                        a = "<if test=\"" + lowerCloum + " != null\">";
                        update.append(a).append("\r\n").append("                ");
                        c = column + " = " + "#{" + lowerCloum + "}";
                        update.append(c).append(",\r\n").append("            ");
                        update.append("</if>").append("\r\n").append("            ");
                    }

                }
            } else if (count == cloumsList.size() - 2) {
                if (!lowerCloum.equals("createDate") && !lowerCloum.equals("delFlag")) {
                    a = "<if test=\"" + lowerCloum + " != null\">";
                    update.append(a).append("\r\n").append("                ");
                    c = column + " = " + "#{" + lowerCloum + "}";
                    update.append(c).append("\r\n").append("            ");
                    update.append("</if>").append("\r\n").append("        ");
                }
            } else if (count == 0) {
                b = "WHERE " + column + " = " + "#{" + lowerCloum + "}";
            }
            count++;
        }
        return update.append("</set>").append("\r\n").append("        ").append(b);
    }

    public StringBuilder listHandler(List<ColumnInfo> cloumsList, List<String> propNameList, List<String> propTypeList) {
        StringBuilder list = new StringBuilder();
        int count = 0;
        for (ColumnInfo columnInfo : cloumsList) {
            String a = "";
            String b = "";
            String column = columnInfo.getName();
            if (count > 0 && count < cloumsList.size() - 1) {
                String propName = propNameList.get(count);
                String propType = propTypeList.get(count);
                if (propType.equals(Boolean.class.getSimpleName()) && propName.length() > 1 && propName.substring(0, 2).equals("is")) {
                    propName = StringUtil.toLowerCaseFirstOne(propName.substring(2));
                }
                if (String.class.getSimpleName().equals(propType)) {
                    a = "<if test=\"" + propName + " != null and " + propName + " != \'\'" + "\">";
                } else {
                    a = "<if test=\"" + propName + " != null" + "\"" + ">";
                }
                list.append(a).append("\r\n").append("            ");
                b = "And a." + column + " = " + "#{" + propName + "}";
                list.append(b).append("\r\n").append("        ");
                if (count >= 0 && count < cloumsList.size() - 2) {
                    list.append("</if>").append("\r\n").append("        ");
                } else {
                    list.append("</if>").append("        ");
                }
            }
            count++;
        }
        return list;
    }
}
