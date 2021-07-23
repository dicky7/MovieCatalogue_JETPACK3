package com.example.moviecatalogue.utils

import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote


object DataMovie {
    fun generateDataMovie():List<MovieEntity> {
        return listOf(
            MovieEntity(
                460465,
                "Mortal Combat",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07"

            ),
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Mar 24, 2021"
            ),
            MovieEntity(
                804435,
                "Vanquish",
                "/b5Ack16C0lDm0AfVkFIGYaZjI83.jpg",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "2021-04-16"
            ),
            MovieEntity(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
            ),
            MovieEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "2020-10-16"
            )
        )
    }

    fun generateDataTv():List<TvEntity> {
        return listOf(
            TvEntity(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26"
            ),
            TvEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19"
            ),
            TvEntity(
                71712,
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2017-09-25"
            ),
            TvEntity(
                60735,
                "The Flash",
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07"
            ),
            TvEntity(
                62286,
                "The Walking Dead",
                "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2015-08-23"
            )
        )
    }

    fun getMovieRemote(): List<MovieRemote>{
        return listOf(
            MovieRemote(
                460465,
                "Mortal Combat",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07"
            ),
            MovieRemote(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Mar 24, 2021"
            ),
            MovieRemote(
                804435,
                "Vanquish",
                "/b5Ack16C0lDm0AfVkFIGYaZjI83.jpg",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "2021-04-16"
            ),
            MovieRemote(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
            ),
            MovieRemote(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "2020-10-16"
            )
        )
    }

    fun getTvRemote(): List<TvRemote>{
        return listOf(
            TvRemote(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26"
            ),
            TvRemote(
                88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19"
            ),
            TvRemote(
                71712,
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2017-09-25"
            ),
            TvRemote(
                60735,
                "The Flash",
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07"
            ),
            TvRemote(
                62286,
                "The Walking Dead",
                "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2015-08-23"
            )
        )
    }
}