package io.junrock.GAZA.domain.message.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -219458194L;

    public static final QMessage message = new QMessage("message");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final NumberPath<Integer> cautionCount = createNumber("cautionCount", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final StringPath donateType = createString("donateType");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final ListPath<io.junrock.GAZA.domain.memberip.entity.MemberIp, io.junrock.GAZA.domain.memberip.entity.QMemberIp> memberIp = this.<io.junrock.GAZA.domain.memberip.entity.MemberIp, io.junrock.GAZA.domain.memberip.entity.QMemberIp>createList("memberIp", io.junrock.GAZA.domain.memberip.entity.MemberIp.class, io.junrock.GAZA.domain.memberip.entity.QMemberIp.class, PathInits.DIRECT2);

    public final NumberPath<Long> messageId = createNumber("messageId", Long.class);

    public final StringPath username = createString("username");

    public final StringPath userRole = createString("userRole");

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata);
    }

}

