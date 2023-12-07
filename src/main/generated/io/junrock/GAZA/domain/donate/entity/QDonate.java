package io.junrock.GAZA.domain.donate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDonate is a Querydsl query type for Donate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonate extends EntityPathBase<Donate> {

    private static final long serialVersionUID = 668017734L;

    public static final QDonate donate = new QDonate("donate");

    public final io.junrock.GAZA.domain.message.entity.QBaseTimeEntity _super = new io.junrock.GAZA.domain.message.entity.QBaseTimeEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final NumberPath<Long> donateId = createNumber("donateId", Long.class);

    public final NumberPath<Long> messageSubId = createNumber("messageSubId", Long.class);

    public final StringPath orderId = createString("orderId");

    public final StringPath paymentKey = createString("paymentKey");

    public final StringPath paymentType = createString("paymentType");

    public QDonate(String variable) {
        super(Donate.class, forVariable(variable));
    }

    public QDonate(Path<? extends Donate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDonate(PathMetadata metadata) {
        super(Donate.class, metadata);
    }

}

