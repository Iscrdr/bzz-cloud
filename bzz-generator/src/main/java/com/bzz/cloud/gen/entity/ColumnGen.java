package com.bzz.cloud.gen.entity;

import lombok.*;

/**
 * @PACKAGE_NAME: com.bzz.cloud.gen.entity
 * @CLASS_NAME: ColumnGen
 * @Author : yang qianli
 * @Date: 2017-12-02 19:52
 * @Modified by:
 * @Date:
 * @Description: Code generator base class of bzz-Cloud project
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ColumnGen {

    /** database table field name **/
    private String columnName;
    /** database table field type **/
    private String columnType;
    /** After the reflected field: the first letter lowercase, the underlined letter capitalization, remove the underline,such as: table field is cloumn_name,java is cloumnName  **/
    private String reflectedColumnName;
    /** database table field comment **/
    private String columnComment;


}
