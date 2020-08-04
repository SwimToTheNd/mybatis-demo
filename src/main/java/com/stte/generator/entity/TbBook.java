package com.stte.generator.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bloodfly
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String author;

    private LocalDate publicationdate;

    private String publication;

    private Double price;

    private String image;

    private String remark;


}
