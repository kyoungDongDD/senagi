package com.ssafy.b105.dto.ocr;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OcrResponseDto {

  private String uid;
  private String name;
  private String inferResult;
  private String message;
  private MatchedTemplate matchedTemplate;
  private ImageField title;
  private List<ImageField> fields;
  private ValidationResult validationResult;
  private ConvertedImageInfo convertedImageInfo;
  private CombineResult combineResult;
  private List<ImageTable> tables;

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class MatchedTemplate {
    private String id;
    private String name;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ValidationResult {
    private String result;
    private String message;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ConvertedImageInfo {
    private Integer width;
    private Integer height;
    private Integer pageIndex;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class CombineResult {
    private String name;
    private String text;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ImageField {
    private String name;
    private String valueType;
    private String inferText;
    private Float inferConfidence;
    private BoundingPoly boundingPoly;
    private String type;
    private Boolean checked;
    private Boolean lineBreak;
    private List<SubField> subFields;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class SubField {
    private BoundingPoly boundingPoly;
    private String inferText;
    private Float inferConfidence;
    private Boolean lineBreak;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ImageTable {
    private BoundingPoly boundingPoly;
    private List<TableCell> cells;
    private Float inferConfidence;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class BoundingPoly {
    private List<Vertices> vertices;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class Vertices {
    private Float x;
    private Float y;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class TableCell {
    private BoundingPoly boundingPoly;
    private List<CellTextLine> cellTextLines;
    private Float inferConfidence;
    private Integer rowSpan;
    private Integer rowIndex;
    private Integer columnSpan;
    private Integer columnIndex;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class CellTextLine {
    private BoundingPoly boundingPoly;
    private Float inferConfidence;
    private List<CellWord> cellWords;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class CellWord {
    private BoundingPoly boundingPoly;
    private Float inferConfidence;
    private String inferText;
  }
}