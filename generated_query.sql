Hibernate: 
    select
        article0_.id as id1_0_,
        article0_.content as content2_0_,
        article0_.published_at as publishe3_0_,
        article0_.slug as slug4_0_,
        article0_.title as title5_0_,
        article0_.fk_user as fk_user7_0_,
        article0_.uuid as uuid6_0_ 
    from
        articles article0_ 
    where
        article0_.slug=?
Hibernate: 
    select
        user0_.id as id1_3_0_,
        user0_.email as email2_3_0_,
        user0_.name as name3_3_0_,
        user0_.password as password4_3_0_,
        user0_.role as role5_3_0_,
        user0_.uuid as uuid6_3_0_ 
    from
        users user0_ 
    where
        user0_.id=?
Hibernate: 
    select
        tags0_.article_id as article_1_1_0_,
        tags0_.tag_id as tag_id2_1_0_,
        tag1_.id as id1_2_1_,
        tag1_.name as name2_2_1_ 
    from
        articles_tags tags0_ 
    inner join
        tags tag1_ 
            on tags0_.tag_id=tag1_.id 
    where
        tags0_.article_id=?
Hibernate: 
    select
        articles0_.tag_id as tag_id2_1_0_,
        articles0_.article_id as article_1_1_0_,
        article1_.id as id1_0_1_,
        article1_.content as content2_0_1_,
        article1_.published_at as publishe3_0_1_,
        article1_.slug as slug4_0_1_,
        article1_.title as title5_0_1_,
        article1_.fk_user as fk_user7_0_1_,
        article1_.uuid as uuid6_0_1_,
        user2_.id as id1_3_2_,
        user2_.email as email2_3_2_,
        user2_.name as name3_3_2_,
        user2_.password as password4_3_2_,
        user2_.role as role5_3_2_,
        user2_.uuid as uuid6_3_2_ 
    from
        articles_tags articles0_ 
    inner join
        articles article1_ 
            on articles0_.article_id=article1_.id 
    inner join
        users user2_ 
            on article1_.fk_user=user2_.id 
    where
        articles0_.tag_id=?
Hibernate: 
    select
        articles0_.tag_id as tag_id2_1_0_,
        articles0_.article_id as article_1_1_0_,
        article1_.id as id1_0_1_,
        article1_.content as content2_0_1_,
        article1_.published_at as publishe3_0_1_,
        article1_.slug as slug4_0_1_,
        article1_.title as title5_0_1_,
        article1_.fk_user as fk_user7_0_1_,
        article1_.uuid as uuid6_0_1_,
        user2_.id as id1_3_2_,
        user2_.email as email2_3_2_,
        user2_.name as name3_3_2_,
        user2_.password as password4_3_2_,
        user2_.role as role5_3_2_,
        user2_.uuid as uuid6_3_2_ 
    from
        articles_tags articles0_ 
    inner join
        articles article1_ 
            on articles0_.article_id=article1_.id 
    inner join
        users user2_ 
            on article1_.fk_user=user2_.id 
    where
        articles0_.tag_id=?
Hibernate: 
    delete 
    from
        articles_tags 
    where
        article_id=?
Hibernate: 
    delete 
    from
        articles 
    where
        id=?
Hibernate: 
    delete 
    from
        tags 
    where
        id=?

//////////////////////////////////////////


Hibernate: 
    select
        user0_.id as id1_3_,
        user0_.email as email2_3_,
        user0_.name as name3_3_,
        user0_.password as password4_3_,
        user0_.role as role5_3_,
        user0_.uuid as uuid6_3_ 
    from
        users user0_ 
    where
        user0_.email=?
Hibernate: 
    select
        tag0_.id as id1_2_,
        tag0_.name as name2_2_ 
    from
        tags tag0_ 
    where
        tag0_.name=?
Hibernate: 
    select
        tag0_.id as id1_2_,
        tag0_.name as name2_2_ 
    from
        tags tag0_ 
    where
        tag0_.name=?
Hibernate: 
    select
        articles0_.tag_id as tag_id2_1_0_,
        articles0_.article_id as article_1_1_0_,
        article1_.id as id1_0_1_,
        article1_.content as content2_0_1_,
        article1_.published_at as publishe3_0_1_,
        article1_.slug as slug4_0_1_,
        article1_.title as title5_0_1_,
        article1_.fk_user as fk_user7_0_1_,
        article1_.uuid as uuid6_0_1_,
        user2_.id as id1_3_2_,
        user2_.email as email2_3_2_,
        user2_.name as name3_3_2_,
        user2_.password as password4_3_2_,
        user2_.role as role5_3_2_,
        user2_.uuid as uuid6_3_2_ 
    from
        articles_tags articles0_ 
    inner join
        articles article1_ 
            on articles0_.article_id=article1_.id 
    inner join
        users user2_ 
            on article1_.fk_user=user2_.id 
    where
        articles0_.tag_id=?
Hibernate: 
    select
        next_val as id_val 
    from
        hibernate_sequence for update
            
Hibernate: 
    update
        hibernate_sequence 
    set
        next_val= ? 
    where
        next_val=?
Hibernate: 
    select
        next_val as id_val 
    from
        hibernate_sequence for update
            
Hibernate: 
    update
        hibernate_sequence 
    set
        next_val= ? 
    where
        next_val=?
Hibernate: 
    insert 
    into
        articles
        (content, published_at, slug, title, fk_user, uuid, id) 
    values
        (?, ?, ?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        tags
        (name, id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        articles_tags
        (article_id, tag_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        articles_tags
        (article_id, tag_id) 
    values
        (?, ?)
Hibernate: 
    select
        article0_.id as id1_0_,
        article0_.content as content2_0_,
        article0_.published_at as publishe3_0_,
        article0_.slug as slug4_0_,
        article0_.title as title5_0_,
        article0_.fk_user as fk_user7_0_,
        article0_.uuid as uuid6_0_ 
    from
        articles article0_ 
    where
        article0_.slug=?
Hibernate: 
    select
        user0_.id as id1_3_0_,
        user0_.email as email2_3_0_,
        user0_.name as name3_3_0_,
        user0_.password as password4_3_0_,
        user0_.role as role5_3_0_,
        user0_.uuid as uuid6_3_0_ 
    from
        users user0_ 
    where
        user0_.id=?
Hibernate: 
    delete 
    from
        articles_tags 
    where
        article_id=?
Hibernate: 
    delete 
    from
        articles 
    where
        id=