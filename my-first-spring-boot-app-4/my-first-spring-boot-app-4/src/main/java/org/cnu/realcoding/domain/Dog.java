package org.cnu.realcoding.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data // getter & setter 포함
@NoArgsConstructor // 기본생성자 포함
@AllArgsConstructor // 사용자지정생성자 포함 (인자로는 모든 인스턴스변수)
public class Dog {
    private String name;
    private String kind;
    private String ownerName;
    private String ownerPhoneNumber;
    private List<String> medicalRecords;
}