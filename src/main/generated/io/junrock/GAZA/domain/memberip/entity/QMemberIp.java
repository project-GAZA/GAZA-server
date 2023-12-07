package io.junrock.GAZA.domain.memberip.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberIp is a Querydsl query type for MemberIp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberIp extends EntityPathBase<MemberIp> {

    private static final long serialVersionUID = 1152153994L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberIp memberIp = new QMemberIp("memberIp");

    public final StringPath ip = createString("ip");

    public final NumberPath<Long> ipId = createNumber("ipId", Long.class);

    public final io.junrock.GAZA.domain.message.entity.QMessage message;

    public final StringPath type = createString("type");

    public QMemberIp(String variable) {
        this(MemberIp.class, forVariable(variable), INITS);
    }

    public QMemberIp(Path<? extends MemberIp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberIp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberIp(PathMetadata metadata, PathInits inits) {
        this(MemberIp.class, metadata, inits);
    }

    public QMemberIp(Class<? extends MemberIp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.message = inits.isInitialized("message") ? new io.junrock.GAZA.domain.message.entity.QMessage(forProperty("message")) : null;
    }

}

