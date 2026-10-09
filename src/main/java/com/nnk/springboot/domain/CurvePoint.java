package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CurveId")
    @NotNull(message = "must not be null")
    private Integer curveId;
    @Column(name = "asOfDate")
    private LocalDateTime asOfDate;
    private Double term;
    private Double value;
    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

}
