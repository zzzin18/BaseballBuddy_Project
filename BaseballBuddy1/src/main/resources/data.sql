--회원
INSERT INTO MEMBER (ID, MEMBER_ID, MEMBER_PW, EMAIL, NICKNAME) VALUES (50, 'blackcat', 'password', 'blackcat@example.com', '검은고양이');
INSERT INTO MEMBER (ID, MEMBER_ID, MEMBER_PW, EMAIL, NICKNAME) VALUES (51, 'daeguuser', 'password', 'daegu@example.com', '대구꿀팁');
INSERT INTO MEMBER (ID, MEMBER_ID, MEMBER_PW, EMAIL, NICKNAME) VALUES (52, 'haeundae', 'password', 'haeundae@example.com', '해운대소녀');
INSERT INTO MEMBER (ID, MEMBER_ID, MEMBER_PW, EMAIL, NICKNAME) VALUES (53, 'wizard123', 'password', 'wizard123@example.com', '마법사123');


-- 리뷰 게시글
INSERT INTO REVIEW_POST
(POST_ID, POST_TITLE, POST_DETAIL, MEMBER_ID, STADIUM, FILTER)
VALUES
(50, '김치말이국수 꼭 드세요!', '왜 항상 줄이 긴 지 이해되는 맛이네요... 줄 서 있는 시간 하나도 안아까워요 꼭 드셔보세요 진짜 최곱니다!! 더위 싹 다 날아가요',
 50, 'JAMSHIL', 'FOOD');

INSERT INTO REVIEW_POST
(POST_ID, POST_TITLE, POST_DETAIL, MEMBER_ID, STADIUM, FILTER)
VALUES
(51, '라팍 주차 만차시 대구미술관쪽 가세요!', '요즘 야구 인기가 너무 많아서 사람들이 너무 많이 오네요 ㅠㅠ 저만 알고 있던 꿀팁인데, 조금 멀어도 대구미술관 무료에요!! 주차비 아끼고 맛있는거 사드세요;',
 51, 'DAEGU', 'PARKING');

INSERT INTO REVIEW_POST
(POST_ID, POST_TITLE, POST_DETAIL, MEMBER_ID, STADIUM, FILTER)
VALUES
(52, '사직 1루 내야필드석 후기', '시야 넓고 괜찮네요 다들 일어서서 응원해서 잘 안 보일 줄 알았는데 괜찮아요 그렇게 좁게 느껴지지도 않았어요',
 52, 'BUSAN', 'FIRSTBASE');

INSERT INTO REVIEW_POST
(POST_ID, POST_TITLE, POST_DETAIL, MEMBER_ID, STADIUM, FILTER)
VALUES
(53, 'kt위즈 스토어 재고 공유해드려요', '스누피 콜라보 상품 다 재고 넉넉해 보여요 물어보니까 지금 품절난 유니폼 사이즈들은 2주 뒤에 입고 예정이라네요 홈 원정 유니폼 재고 넉넉합니다',
 53, 'SUWON', 'GOODS');

-- 직관모집 게시글
INSERT INTO ATTEND_POST (POST_ID, POST_TITLE, POST_DETAIL, POST_DATE, MEMBER_ID) VALUES
(50, '6월 10일 엘지 직관 같이 가실 분~', '잠실 1루 3열이에요! 얼음물 챙겨올게요 저녁 6시쯤 만나요~', '2025-06-10', 50),
(51, '롯데 vs 삼성 직관', '대구 경기입니다. 외야 좌익수쪽 자리 잡아서 같이 보실분!!', '2025-06-23',51),
(52, '23일 사직에서 경기 볼 삼펜 가족 없나용?', '같이 올드 유니폼 맞춰 입어요! 7살 남자아이랑 같이 볼 가족 찾습니다~ 친구 만들어주고 싶어요!', '2025-06-23', 52);

UPDATE ATTEND_POST
SET ATTEND_DATE = '2025-06-10'
WHERE POST_ID = 50;

UPDATE ATTEND_POST
SET ATTEND_DATE = '2025-06-23'
WHERE POST_ID IN (51, 52);


-- 댓글
INSERT INTO COMMENT (COMMENT_ID, COMMENT_DETAIL, MEMBER_ID, ATTEND_POST_ID) VALUES
(50, '나도 갈래요!', 50, 50),
(51, '얼음물 꼭 챙겨주세요!', 50, 50),
(52, '외야 자리 좋은데~', 51, 51);




