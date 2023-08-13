package com.kaanyagan.wowiemax.data

import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.entity.model.Actor
import com.kaanyagan.wowiemax.data.entity.model.ActorMovie
import com.kaanyagan.wowiemax.data.entity.model.Category
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.data.entity.model.User

object Database {
    var users = mutableListOf(
        User(1,"Elif","elif@gmail.com","12345"),
        User(2,"Kaan","kaan@gmail.com","12345"),
        User(3,"Salih","salih@gmail.com","12345"),
        User(4,"Nejat","nejat@gmail.com","12345"),
        User(5,"a","a","a"),
    )

    val actors = listOf(
        Actor(1,"Shameik","Moore", listOf(
            ActorMovie(1,"Miles Morales/Spider-Man")),"https://tr.web.img3.acsta.net/c_162_216/pictures/14/12/05/13/45/453276.jpg"),

        Actor(2,"Hailee","Steinfeld", listOf(
            ActorMovie(1,"Gwen Stacey/Spider-Gwen")
        ),"https://tr.web.img2.acsta.net/c_162_216/pictures/19/08/27/10/13/0826817.jpg"),

        Actor(3,"Oscar","Isaac", listOf(
            ActorMovie(1,"Miguel OHara/Spider-Man 2099"),
        ),"https://avatars.mds.yandex.net/i?id=1edd56c83b8709292878510693f6037025b8e399-7546740-images-thumbs&n=13"),

        Actor(4,"Issa","Rae", listOf(
            ActorMovie(1,"Jessica Drew/Spider-Woman")
        ),"https://tr.web.img2.acsta.net/c_162_216/pictures/16/01/27/17/54/439095.jpg"),

        Actor(5,"Austin","Lin", listOf(
            ActorMovie(9,"Mao Pang-yu")
        ),"https://i.mydramalist.com/Z88lyO_5f.jpg"),

        Actor(6,"Aaron","Yan", listOf(
            ActorMovie(9,"Chen Chia-Hao")
        ),"https://m.media-amazon.com/images/M/MV5BOTdjN2UzYzUtMzJjYi00MGRiLWI0NmUtMzI1YWYzZGU0NDNjXkEyXkFqcGdeQXVyMjg0MTI5NzQ@._V1_FMjpg_UX1000_.jpg"),

        Actor(7,"Gingle","Wang", listOf(
            ActorMovie(9,"Lin Tzu-ching"),ActorMovie(11,"David"),
        ),"https://i.mydramalist.com/BYJkb_5f.jpg"),

        Actor(8,"Man-Chiao ","Wang", listOf(
            ActorMovie(9,"Mao Chen A-lan")
        ),"https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Michael_Jai_White_2016.jpg/665px-Michael_Jai_White_2016.jpg"),

        Actor(9,"Michael Jai","White", listOf(
            ActorMovie(2,"Mark"),ActorMovie(11,"London"),
        ),"https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Michael_Jai_White_2016.jpg/665px-Michael_Jai_White_2016.jpg"),

        Actor(10,"Gillian","White", listOf(
            ActorMovie(2,"Akilah")
        ),"https://m.media-amazon.com/images/M/MV5BZjU3ZmUzNzYtMTE4Yy00MTZhLWE1ODYtYWM2MzQyMjFjNTBhXkEyXkFqcGdeQXVyNTI4ODY2NjE@._V1_FMjpg_UX1000_.jpg"),

        Actor(11,"Jackson","Rathbone", listOf(
            ActorMovie(2,"Phil"), ActorMovie(11,"Oppenheimer"),
        ),"https://m.media-amazon.com/images/M/MV5BMTE2ZTQ0NDctYTA4YS00YzIzLTgzODctMjUyOWM0NTI0NDRhXkEyXkFqcGdeQXVyMjUzMTE1MzA@._V1_FMjpg_UX1000_.jpg"),

        Actor(12,"Edoardo","Costa", listOf(
            ActorMovie(2,"Manuel"),ActorMovie(11,"John"),
        ),"https://upload.wikimedia.org/wikipedia/commons/9/9c/EdoardoCosta.jpg"),

        Actor(13,"Shameik","Moore", listOf(
            ActorMovie(3,"Miles Morales/Spider-Man")),"https://tr.web.img3.acsta.net/c_162_216/pictures/14/12/05/13/45/453276.jpg"),

        Actor(14,"Hailee","Steinfeld", listOf(
            ActorMovie(3,"Gwen Stacey/Spider-Gwen")
        ),"https://tr.web.img2.acsta.net/c_162_216/pictures/19/08/27/10/13/0826817.jpg"),

        Actor(15,"Oscar","Isaac", listOf(
            ActorMovie(3,"Miguel OHara/Spider-Man 2099"),ActorMovie(11,"Staff"),
        ),"https://avatars.mds.yandex.net/i?id=1edd56c83b8709292878510693f6037025b8e399-7546740-images-thumbs&n=13"),

        Actor(16,"Issa","Rae", listOf(
            ActorMovie(3,"Jessica Drew/Spider-Woman")
        ),"https://tr.web.img2.acsta.net/c_162_216/pictures/16/01/27/17/54/439095.jpg"),

        Actor(17,"Keanu","Reeve", listOf(
            ActorMovie(12,"John Wick")
        ),"https://i4.hurimg.com/i/hurriyet/75/750x422/600965400f25443f0406f3ab.jpg"),

        Actor(18,"Lance","Reddick", listOf(
            ActorMovie(12,"Charon"),ActorMovie(5,"Knife")
        ),"https://image.posta.com.tr/i/posta/75/0x0/64155cb4e4bfdd196406685b.jpg"),

        Actor(19,"Harrison","Ford", listOf(
            ActorMovie(14,"Indiana Jones")
        ),"https://hips.hearstapps.com/hmg-prod/images/harrison-ford-indiana-jones-649da0a102993.jpeg?crop=0.752xw:1.00xh;0.129xw,0&resize=1200:*"),

        Actor(20,"Antonio","Banderas", listOf(
            ActorMovie(14,"Renaldo")
        ),"https://www.donanimhaber.com/images/images/haber/136356/src/indiana-jones-5-kadrosuna-antonio-banderas-da-katiliyor136356_0.jpg"),

        Actor(21,"Clint","Eastwood",listOf(
            ActorMovie(21,"Blondie")),"https://turkcealtyazi.org/film/nmmid/nm0000142.jpg"
        ),

        Actor(22,"Jim","Carrey",listOf(
            ActorMovie(22,"Stanley Ipkiss")),"https://turkcealtyazi.org/film/nmbig/nm0000120.jpg"
        ),

        Actor(23,"Audrey","Fleurot",listOf(
            ActorMovie(23,"Magalie")),"https://turkcealtyazi.org/film/nmmid/nm1109153.jpg"
        ),

        Actor(24,"François", "Cluzet",listOf(
            ActorMovie(24,"Philippe")),"https://turkcealtyazi.org/film/nmmid/nm0167388.jpg"
        ),

        Actor(25,"Amy","Yasbeck",listOf(
            ActorMovie(25,"Peggy Brandt")),"https://turkcealtyazi.org/film/nmbig/nm0001865.jpg"
        ),

        Actor(26,"Clare","Perkins", listOf(
            ActorMovie(4, "Cleve")
        ),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIYFd55yrT_lgriT0kXd7SJlExDOvWaFgoxQ&usqp=CAU"),

        Actor(37,"Anita-Joy","Uwajeh", listOf(
            ActorMovie(4, "Timba"),
        ),"https://tr.web.img2.acsta.net/c_310_420/pictures/23/06/01/20/46/2495426.jpg"),

        Actor(28,"Harriet","Webb", listOf(
            ActorMovie(4, "Kendra")
        ),"https://tr.web.img4.acsta.net/c_310_420/pictures/23/06/01/20/53/5592309.jpg"),

        Actor(29,"Melissa","Barrera", listOf(
            ActorMovie(5, "Sam Carpenter")
        ),"https://tr.web.img2.acsta.net/c_310_420/pictures/20/02/14/16/18/1997476.jpg"),

        Actor(30,"Mason","Gooding", listOf(
            ActorMovie(5, "Chad Meeks-Martin"),
        ),"https://tr.web.img4.acsta.net/c_310_420/pictures/19/07/03/01/04/5081775.jpg"),

        Actor(31,"Dermot","Mulroney", listOf(
            ActorMovie(5,"Detective Bailey")
        ),"https://tr.web.img3.acsta.net/c_310_420/pictures/17/05/31/12/06/540352.jpg"),

        Actor(32,"Jim","Carrey", listOf(
            ActorMovie(6, "Bruce Nolan")
        ),"https://tr.web.img4.acsta.net/c_310_420/medias/nmedia/18/35/26/86/20284791.jpg"),

        Actor(33,"Jennifer","Aniston", listOf(
            ActorMovie(6, "Grace Connelly"),
        ),"https://tr.web.img4.acsta.net/c_162_216/pictures/16/05/24/16/03/505445.jpg"),

        Actor(34,"Morgan","Freeman", listOf(
            ActorMovie(6,"God")
        ),"https://tr.web.img4.acsta.net/c_162_216/pictures/16/01/19/17/12/251122.jpg"),

        Actor(235,"Jackie","Chan", listOf(
            ActorMovie(7, "Luo Feng"),
            ActorMovie(8, "Splinter")
        ),"https://tr.web.img4.acsta.net/c_162_216/pictures/16/01/07/16/44/109721.jpg"),

        Actor(36,"John","Cena", listOf(
            ActorMovie(7, "Chris Van Horne"),
            ActorMovie(8,"Rocksteady"),
        ),"https://tr.web.img4.acsta.net/c_162_216/pictures/17/06/14/13/48/489688.jpg"),

        Actor(37,"Pilou","Asbæk", listOf(
            ActorMovie(7,"Owen Paddock")
        ),"https://tr.web.img3.acsta.net/c_162_216/pictures/15/08/11/16/48/334466.jpg"),

        Actor(38,"Shamon","Brown Jr.", listOf(
            ActorMovie(8, "Michelangelo")
        ),"https://tr.web.img3.acsta.net/c_310_420/pictures/17/11/21/17/42/0859211.jpg"),

        Actor(39,"Hannibal","Buress", listOf(
            ActorMovie(8, "Genghis Frog"),
        ),"https://tr.web.img2.acsta.net/c_310_420/pictures/15/11/27/14/51/439121.jpg"),

        Actor(40,"Rose","Byrne", listOf(
            ActorMovie(8,"Leatherhead")
        ),"https://tr.web.img2.acsta.net/c_310_420/medias/nmedia/18/35/52/84/19786784.jpg"),

        Actor(41,"Joel","Courtney", listOf(
            ActorMovie(10, "Greg Laurie")
        ),"https://tr.web.img4.acsta.net/c_310_420/medias/nmedia/18/84/74/30/19786958.jpg"),

        Actor(42,"Kimberly","Williams-Paisley", listOf(
            ActorMovie(10, "Charlene"),
        ),"https://tr.web.img3.acsta.net/c_310_420/medias/nmedia/18/95/02/83/20373040.jpg"),

        Actor(43,"Kelsey","Grammer", listOf(
            ActorMovie(10,"Chuck Smith")
        ),"https://tr.web.img2.acsta.net/c_310_420/medias/nmedia/18/36/21/37/20190546.jpg"),

        Actor(44,"Cillian","Murphy", listOf(
            ActorMovie(11, "J. Robert Oppenheimer")
        ),"https://tr.web.img2.acsta.net/c_310_420/pictures/15/09/25/11/13/078050.jpg"),

        Actor(45,"Emily","Blunt", listOf(
            ActorMovie(11, "Kitty Oppenheimer"),
        ),"https://tr.web.img4.acsta.net/c_310_420/pictures/15/05/19/14/51/233236.jpg"),

        Actor(45,"Matt","Damon", listOf(
            ActorMovie(11,"Leslie Groves Jr.")
        ),"https://tr.web.img3.acsta.net/c_310_420/pictures/16/07/13/11/16/193188.jpg"),

    )

    val movies = listOf(
        Movie(1,R.string.spider_man_across_the_spider_verse_name,"140","https://www.hdfilmcehennemi.life/uploads/poster/spider-man-across-the-spider-verse.jpg","Marvel","Joaquim Dos Santos","Phil Lord",96, listOf(
            Category.Animasyon,Category.Aksiyon,Category.Macera),R.string.spider_man_across_the_spider_verse_content, 10, isViolence = true, isNegativeExample = true),

        Movie(2,R.string.the_island_name,"93","https://www.hdfilmcehennemi.life/uploads/poster/the-island.jpg","Wowie","Michael Bay","Caspian Tredwell-Owen",77, listOf(
            Category.Aksiyon,Category.Gerilim
        ),R.string.the_island_content,16, isViolence = true, isNegativeExample = true),

        Movie(3,R.string.saw_X_name,"118","https://www.hdfilmcehennemi.life/uploads/poster/saw-x.jpg","Wowie","Kevin Greutert","Josh Stolberg",90, listOf(
            Category.Korku,Category.Gerilim,Category.Gizem
        ),R.string.saw_X_content,18, isViolence = false, isNegativeExample = false),

        Movie(4,R.string.medusa_deluxe_name,"101","https://www.hdfilmcehennemi.life/uploads/poster/medusa-deluxe.jpg","Wowie","Thomas Hardiman","Thomas Hardiman",65, listOf(
            Category.Dram,Category.Gizem
        ),R.string.medusa_deluxe_content,13, isViolence = false, isNegativeExample = true),

        Movie(5,R.string.scream_VI_name,"122","https://www.hdfilmcehennemi.life/uploads/poster/scream-6-2.jpg","Paramount Pictures","Tyler Gillett","James Vanderbilt",76, listOf(
            Category.Korku,Category.Gerilim,Category.Gizem
        ),R.string.scream_VI_content,18, isViolence = false, isNegativeExample = false),

        Movie(6,R.string.bruce_almighty_name,"101","https://www.hdfilmcehennemi.life/uploads/poster/aman-tanrim-1-izle.jpg","Spyglass Media Group","Tom Shadyac","Steve Koren",88, listOf(
            Category.Komedi,Category.Fantastik
        ),R.string.bruce_almighty_content,16, isViolence = true, isNegativeExample = true),

        Movie(7,R.string.hidden_strike_name,"103","https://www.hdfilmcehennemi.life/uploads/poster/hidden-strike.jpg","CHANTIER FILMS","Scott Waugh","Arash Amel",80, listOf(
            Category.Aksiyon,Category.Macera,Category.Komedi
        ),R.string.hidden_strike_content,16, isViolence = true, isNegativeExample = false),

        Movie(8,R.string.teenage_mutant_ninja_turtles__mutant_mayhem_name,"99","https://www.hdfilmcehennemi.life/uploads/poster/teenage-mutant-ninja-turtles-mutant-mayhem.jpg","Point Grey Pictures","Jeff Rowe","Brendan O'Brien",91, listOf(
            Category.Aksiyon,Category.Macera,Category.Animasyon
        ),R.string.teenage_mutant_ninja_turtles__mutant_mayhem_content,7, isViolence = true, isNegativeExample = true),

        Movie(9,R.string.marry_my_dead_body_name,"130","https://www.hdfilmcehennemi.life/uploads/poster/marry-my-dead-body.jpg","QC Media","Cheng Wei-hao","Cheng Wei-hao",75, listOf(
            Category.Aksiyon,Category.Komedi,Category.Dram
        ),R.string.marry_my_dead_body_content,18, isViolence = true, isNegativeExample = true),

        Movie(10,R.string.jesus_revolution_name,"120","https://www.hdfilmcehennemi.life/uploads/poster/jesus-revolution.jpg"," Lions Gate Entertainment","Jon Erwin","Jon Erwin",71, listOf(Category.Dram),R.string.jesus_revolution_content,16, isViolence = true, isNegativeExample = true),

        Movie(11,R.string.oppenheimer_name,"180","https://www.hdfilmcehennemi.life/uploads/poster/oppenheimer.jpg","Syncopy","Christopher Nolan","Christopher Nolan",94, listOf(
            Category.Dram,Category.Biyografi,Category.Tarih
        ),R.string.oppenheimer_content,13, isViolence = true, isNegativeExample = true),

        Movie(12,R.string.john_wick_name,"141","https://www.hdfilmcehennemi.life/uploads/poster/hd-2-john-wick-izle.jpg","Blazer","David Leitch","Derek Kolstad",92,
            listOf(Category.Aksiyon,Category.Gerilim),R.string.john_wick_content,14,true,true),

        Movie(13,R.string.Sniper_The_White_Raven_name,"120","https://www.hdfilmcehennemi.life/uploads/poster/sniper-the-white-raven.jpg","Blazer","Marian Bushan","Marian Bushan",85,
            listOf(Category.Aksiyon,Category.Tarih,Category.Gerilim),R.string.sniper_content,10,true,true),

        Movie(14,R.string.Indiana_Jones_name,"135","https://www.hdfilmcehennemi.life/uploads/poster/indiana-jones-and-the-dial-of-destiny.jpg","Blazer","James Mangold","James Mangold",95,
            listOf(Category.Aksiyon,Category.Macera,Category.Fantastik),R.string.indiana_jones_content,10,true,true),

        Movie(15,R.string.intouchables,"112","https://www.hdfilmcehennemi.life/uploads/poster/hd-can-dostum-izle-2.jpg","Gaumont Film Company","Oliver Nakache","Éric Toledano",85,listOf(
            Category.Dram,Category.Komedi),R.string.intouchables_content,18,isViolence = true,isNegativeExample = true),

        Movie(16,R.string.the_mask,"101","https://www.hdfilmcehennemi.life/uploads/poster/1-maske-izle.jpg","Dark Horse Entertainment","Chuck Russell","Michael Fallon",75,listOf(
            Category.Aksiyon,Category.Komedi,Category.Fantastik),R.string.the_mask_content,13,isViolence = true,isNegativeExample = true),

        Movie(17,R.string.the_good_the_bad_and_the_ugly,"161","https://www.hdfilmcehennemi.life/uploads/poster/1-iyi-kotu-ve-cirkin-izle.jpg","Produzioni Europee Associati","Sergio Leone","Luciano Vincenzoni",90,listOf(
            Category.Macera),R.string.the_good_the_bad_and_the_ugly_content,15,isViolence = true,isNegativeExample = true)
    )
}