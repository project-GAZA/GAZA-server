package io.junrock.GAZA.domain.message.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.entity.QMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static io.junrock.GAZA.domain.message.entity.QMessage.message;


@Repository
@RequiredArgsConstructor
public class MessageQueryRepository {
    private final EntityManager em;

    public List<Message> findMessages(Pageable pageable,String type) {

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMessage message = QMessage.message;
        return query
                .select(message)
                .from(message)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier(type))
                .fetch();
    }


    private OrderSpecifier<String> orderSpecifier(String type) {
        Order order = Order.DESC;
        switch (type) {
            case "new":
                return new OrderSpecifier(order, message.createDt);
            case "best":
                return new OrderSpecifier(order, message.likeCount);
        }
        return null;
    }
}
