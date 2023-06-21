package com.unexus.minegocio.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase genirica para respuestas
 *
 * @param <T>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseVo<T> {
	@Builder.Default
    private Integer code = 200;
    private String message;
    private List<String> errors;
    private T data;
}