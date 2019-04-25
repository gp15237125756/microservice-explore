package com.rbsn.schedule.common.util;

import com.rbsn.schedule.common.constants.Constants;
import com.rbsn.schedule.common.support.generator.*;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成类
 */
public class MybatisGeneratorUtil {

    private static final Logger logger = LoggerFactory.getLogger(MybatisGeneratorUtil.class);

    /**
     * 生成类
     *
     * @param jdbcDriver
     * @param jdbcUrl
     * @param jdbcUsername
     * @param jdbcPassword
     * @param tableName
     * @param packageName
     * @param isAdd
     */
    public static void generator(String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword,
                                 String tableName, String packageName, Boolean isAdd) throws Exception {
        String serviceVm = "/template/Service.vm";
        String serviceImplVm = "/template/ServiceImpl.vm";
        String pojoVm = "/template/Pojo.vm";
        String daoVm = "/template/Dao.vm";
        String mapperVm = "/template/MapperXml.vm";
        serviceVm = MybatisGeneratorUtil.class.getResource(serviceVm).getPath().replaceFirst("/", "");
        serviceImplVm = MybatisGeneratorUtil.class.getResource(serviceImplVm).getPath().replaceFirst("/", "");
        pojoVm = MybatisGeneratorUtil.class.getResource(pojoVm).getPath().replaceFirst("/", "");
        mapperVm = MybatisGeneratorUtil.class.getResource(mapperVm).getPath().replaceFirst("/", "");
        daoVm = MybatisGeneratorUtil.class.getResource(daoVm).getPath().replaceFirst("/", "");
        PropertyUtil.loadProp("columnType2PropType.properties");
        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath()
                .replace("/target/classes/", "").replaceFirst("/", "");
        int lastIndexOf = basePath.lastIndexOf("/");
        basePath = basePath.substring(0, lastIndexOf);
        JdbcHelper jdbcUtil = new JdbcHelper();
        Connection conn = jdbcUtil.conn(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
        DatabaseMetaData dbMetaData = conn.getMetaData();
        String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());

        logger.info("========== 开始生成Pojo ==========");
        String facadePojo = packageName.substring(18);
        String pojoPath = basePath + "/" + "schedule" + "-facade-" + facadePojo + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/pojo";
        File file = new File(pojoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        TableInfo tableInfo = new TableInfo();
        ResultSet columnRS = dbMetaData.getColumns(null, "root", tableName, Constants.EMPTY_STR);
        List<ColumnInfo> columnList = new ArrayList<>();
        while (columnRS.next()) {
            String columnName = columnRS.getString("COLUMN_NAME").toLowerCase();
            String columnType = columnRS.getString("TYPE_NAME").toLowerCase();
            String columnRemark = columnRS.getString("REMARKS");
            int len = columnRS.getInt("COLUMN_SIZE");
            int precision = columnRS.getInt("DECIMAL_DIGITS");
            ColumnInfo ci = new ColumnInfo();
            ci.setName(columnName);
            ci.setType(columnType);
            ci.setRemark(columnRemark);
            ci.setLen(len);
            ci.setPrecision(precision);
            columnList.add(ci);
        }
        tableInfo.setName(tableName);
        tableInfo.setColumnList(columnList);
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setTableName(tableName);
        Map<String, String> propTypes = new LinkedHashMap<>();
        Map<String, String> propRemarks = new LinkedHashMap<>();
        List<String> propNameList = new ArrayList<>();
        List<String> propTypeList = new ArrayList<>();
        for (ColumnInfo columnInfo : columnList) {
            String fieldName = columnInfo.getName();
            String fieldType = columnInfo.getType();
            // 通过字段名生成属性名
            String propName = StringUtil.convertFieldName2PropName(fieldName);
            // 这里为了兼容oracle，number类型，如果小数精度为0，则映射成Long类型
            String propType;
            if (Constants.DBTYPE_NUMBER.equals(fieldType) && columnInfo.getPrecision() == 0) {
                propType = Constants.PROPTYPE_LONG;
            } else {
                propType = PropertyUtil.getValueByKey(fieldType);
            }
            // 只有is开头的char类型转为布尔类型
            if (fieldType.equals("char") && propName.length() > 1 && propName.substring(0, 2).equals("is")) {
                propType = Boolean.class.getSimpleName();
            }
            propTypes.put(propName, propType);
            propRemarks.put(propName, columnInfo.getRemark());
            propNameList.add(propName);
            propTypeList.add(propType);
        }
        entityInfo.setPropTypes(propTypes);
        entityInfo.setPropRemarks(propRemarks);

        PojoHandler pojoHandler = new PojoHandler();
        StringBuilder propertiesStr = pojoHandler.columnHandler(entityInfo);
        StringBuilder methodStr = pojoHandler.setterGetterHandler(entityInfo);
        String model = StringUtil.lineToHump(tableName);
        String sToString = pojoHandler.toStringHandler(entityInfo, model);
        String pojo = pojoPath + "/" + model + "DO.java";
        // 生成pojo
        File pojoFile = new File(pojo);
        if (pojoFile.exists()) {
            pojoFile.delete();
        }
        VelocityContext context = new VelocityContext();
        context.put("package_name", packageName);
        context.put("model", model);
        context.put("ctime", ctime);
        context.put("propertiesStr", propertiesStr);
        context.put("methodStr", methodStr);
        context.put("sToString", sToString);
        VelocityUtil.generate(pojoVm, pojo, context);
        logger.info("========== 结束生成Pojo ==========");

        if (isAdd) {
            logger.info("========== 开始生成Dao ==========");
            String serviceDao = packageName.substring(18);
            String daoPath = basePath + "/" + "schedule" + "-service-" + serviceDao + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/dao";
            file = new File(daoPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String dao = daoPath + "/" + model + "Dao.java";
            // 生成pojo
            File daoFile = new File(dao);
            if (daoFile.exists()) {
                daoFile.delete();
            }
            context = new VelocityContext();
            context.put("package_name", packageName);
            context.put("model", model);
            context.put("ctime", ctime);
            VelocityUtil.generate(daoVm, dao, context);
            logger.info("========== 结束生成Dao ==========");

            logger.info("========== 开始生成Service ==========");
            String facadeService = packageName.substring(18);
            String serviceServiceImpl = packageName.substring(18);
            String servicePath = basePath + "/" + "schedule" + "-facade-" + facadeService + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/service";
            String serviceImplPath = basePath + "/" + "schedule" + "-service-" + serviceServiceImpl + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/service/impl";
            File fileService = new File(servicePath);
            File fileServiceImpl = new File(serviceImplPath);
            if (!fileService.exists()) {
                fileService.mkdir();
            }
            if (!fileServiceImpl.exists()) {
                fileServiceImpl.mkdir();
            }
            String service = servicePath + "/" + model + "Service.java";
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            // 生成service
            File serviceFile = new File(service);
            if (serviceFile.exists()) {
                serviceFile.delete();

            }
            context = new VelocityContext();
            context.put("package_name", packageName);
            context.put("model", model);
            context.put("ctime", ctime);
            VelocityUtil.generate(serviceVm, service, context);
            // 生成serviceImpl
            File serviceImplFile = new File(serviceImpl);
            if (serviceImplFile.exists()) {
                serviceImplFile.delete();
            }
            context.put("package_name", packageName);
            context.put("model", model);
            context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
            context.put("ctime", ctime);
            VelocityUtil.generate(serviceImplVm, serviceImpl, context);
            logger.info("========== 结束生成Service ==========");
        }

        logger.info("========== 开始生成MapperXml ==========");
        String serviceMapper = packageName.substring(18);
        String mapperXmlPath = basePath + "/" + "schedule" + "-service-" + serviceMapper + "/src/main/resources/mybatis/mappings";
        MapperHandler mapperHandler = new MapperHandler();
        String baseColumn = mapperHandler.sqlHandler(columnList);
        StringBuilder insert = mapperHandler.insertHandler(columnList, tableName);
        StringBuilder update = mapperHandler.updateHandler(columnList, tableName);
        StringBuilder list = mapperHandler.listHandler(columnList, propNameList, propTypeList);
        file = new File(mapperXmlPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String modelLower = StringUtil.toLowerCaseFirstOne(model);
        String mapperXml = mapperXmlPath + "/" + model + "Dao.xml";
        // 生成mapper.xml
        File mapperFile = new File(mapperXml);
        if (mapperFile.exists()) {
            mapperFile.delete();
        }
        context = new VelocityContext();
        context.put("package_name", packageName);
        context.put("model", model);
        context.put("ctime", ctime);
        context.put("table", tableName);
        context.put("baseColumn", baseColumn);
        context.put("insert", insert);
        context.put("update", update);
        context.put("modelLower", modelLower);
        context.put("list", list);
        VelocityUtil.generate(mapperVm, mapperXml, context);
        jdbcUtil.release();
        logger.info("========== 结束生成MapperXml ==========");
    }
}
