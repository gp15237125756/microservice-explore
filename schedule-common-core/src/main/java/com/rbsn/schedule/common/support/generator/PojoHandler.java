package com.rbsn.schedule.common.support.generator;


import com.rbsn.schedule.common.util.StringUtil;

import java.util.Map;

/**
 * 自动代码生成处理
 */
public class PojoHandler {
    public StringBuilder columnHandler(EntityInfo entityInfo) {
        StringBuilder sb = new StringBuilder();
        // 生成属性
        sb = new StringBuilder();
        int count=0;
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Map.Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();
            String sPropName="";
            // 注释、类型、名称
            if(count!=0){
                if(!propName.equals("createDate")&&!propName.equals("updateDate")&&!propName.equals("remark")
                &&!propName.equals("delFlag")){
                    if(propType.equals("Boolean")){
                        if(propName.substring(0,2).equals("is")){
                            sPropName= StringUtil.toLowerCaseFirstOne(propName.substring(2));
                        }else {
                            sPropName=propName;
                        }

                        sb.append("/**")
                                .append("\r\n").append("     ").append("*").append(" ")
                                .append(propRemarks.get(propName))
                                .append("\r\n").append("     ")
                                .append("*/\r\n").append("    ")
                                .append("private ")
                                .append(propType)
                                .append(" ")
                                .append(sPropName)
                                .append(";\r\n").append("    ").append("\r\n").append("    ");
                    }else {
                        sb.append("/**")
                                .append("\r\n").append("     ").append("*").append(" ")
                                .append(propRemarks.get(propName))
                                .append("\r\n").append("     ")
                                .append("*/\r\n").append("    ")
                                .append("private ")
                                .append(propType)
                                .append(" ")
                                .append(propName)
                                .append(";\r\n").append("    ").append("\r\n").append("    ");
                    }
                }
            }
            count++;
        }
        return sb;
    }
    public StringBuilder setterGetterHandler(EntityInfo entityInfo) {
        //getter,setter方法
        StringBuilder sbMethods = new StringBuilder();
        int count=0;
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Map.Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();
            String sPropName="";
            if (count!=0){
                if(!propName.equals("createDate")&&!propName.equals("updateDate")&&!propName.equals("remark")
                        &&!propName.equals("delFlag")){
                    if(propType.equals("Boolean")){
                        if(propName.substring(0,2).equals("is")){
                            sPropName= StringUtil.toLowerCaseFirstOne(propName.substring(2));
                        }else {
                            sPropName=propName;
                        }
                        sbMethods.append("public ")
                                .append(propType)
                                .append(" ")
                                .append(propName)
                                .append("() {\r\n")
                                .append("        return ")
                                .append(sPropName)
                                .append(";\r\n")
                                .append("    }\r\n").append("\n")
                                .append("    public void set")
                                .append(sPropName.substring(0, 1).toUpperCase())
                                .append(sPropName.substring(1))
                                .append("(")
                                .append(propType)
                                .append(" ")
                                .append(sPropName)
                                .append(") {\r\n")
                                .append("        this.")
                                .append(sPropName)
                                .append(" = ")
                                .append(sPropName)
                                .append(";\r\n    }\r\n")
                                .append("\r\n").append("    ");
                    }else {
                        sbMethods.append("public ")
                                .append(propType)
                                .append(" get")
                                .append(propName.substring(0, 1).toUpperCase())
                                .append(propName.substring(1))
                                .append("() {\r\n")
                                .append("        return ")
                                .append(propName)
                                .append(";\r\n")
                                .append("    }\r\n").append("\n")
                                .append("    public void set")
                                .append(propName.substring(0, 1).toUpperCase())
                                .append(propName.substring(1))
                                .append("(")
                                .append(propType)
                                .append(" ")
                                .append(propName)
                                .append(") {\r\n")
                                .append("        this.")
                                .append(propName)
                                .append(" = ")
                                .append(propName)
                                .append(";\r\n    }\r\n")
                                .append("\r\n").append("    ");
                    }
                }
            }
            count++;
            }
        return sbMethods;
    }

    public String toStringHandler(EntityInfo entityInfo, String model) {
        StringBuilder sToString = new StringBuilder();
        int count=0;
        // 生成属性
        sToString = new StringBuilder();
        sToString.append("\"").append(model).append("DO{").append("\"").append("+ ").append("super.toString()").append("+")
                .append("\r\n").append("                    ");
        for (Map.Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();
            String sPropName="";
            if (count!=0){
                if(!propName.equals("createDate")&&!propName.equals("updateDate")&&!propName.equals("remark")
                        &&!propName.equals("delFlag")){
                    if(propType.equals("Boolean")){
                        if(propName.substring(0,2).equals("is")){
                            sPropName= StringUtil.toLowerCaseFirstOne(propName.substring(2));
                        }else {
                            sPropName=propName;
                        }
                        sToString.append("\", ").append(sPropName).append("=").append("\"").append("+").append(sPropName).append("+")
                                .append("\r\n").append("                    ");
                    }else {
                        sToString.append("\", ").append(propName).append("=").append("\"").append("+").append(propName).append("+")
                                .append("\r\n").append("                    ");
                    }
                }
            }
            count++;
        }
        sToString.append("\'}\'").append(";");
        return sToString.substring(0,sToString.length());
    }

}
