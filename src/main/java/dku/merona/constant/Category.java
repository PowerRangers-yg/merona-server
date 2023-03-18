package dku.merona.constant;

import dku.merona.constant.config.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Category implements EnumMapperType {
    FOOD("식품"),
    BOOK("도서류"),
    NECESSITY("생필품"),
    ELECTRONIC_DEVICE("전자기기"),
    CLOTHING("의류"),
    STATIONERY("문구"),
    ETC("기타");

    @Getter
    private final String title;

    @Override
    public String getCode() {
        return name();
    }

    public static Category ofCode(String title) {
        return Arrays.stream(Category.values())
                .filter(v -> v.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다"));
    }
}
