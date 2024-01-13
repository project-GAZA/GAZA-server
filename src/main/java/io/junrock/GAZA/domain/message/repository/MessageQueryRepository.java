package io.junrock.GAZA.domain.message.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.junrock.GAZA.domain.message.dto.MessageSearchDto;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.entity.QMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static io.junrock.GAZA.domain.message.entity.QMessage.message;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class MessageQueryRepository {
    private final EntityManager em;
    public List<Message> findMessages(Pageable pageable, MessageSearchDto messageSearchDto) {

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMessage message = QMessage.message;
        return query
                .selectFrom(message)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(usernameEq(messageSearchDto.getUsername()))
                .orderBy(orderSpecifier(messageSearchDto.getSort()))
                .fetch();
    }

    private OrderSpecifier<String> orderSpecifier(String sort) {
        Order order = Order.DESC;
        switch (sort) {
            case "new":
                return new OrderSpecifier(order, message.createDt);
            case "best":
                return new OrderSpecifier(order, message.likeCount);
        }
        return null;
    }

    private BooleanExpression usernameEq(String username){
        return hasText(username)?message.username.like(username+"%"):null;
    }
}
