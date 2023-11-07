package com.xbot.goodnotes.data.repository

import com.xbot.goodnotes.data.models.FolderEntity
import com.xbot.goodnotes.data.models.NoteEntity
import com.xbot.goodnotes.data.models.NoteFolderCrossRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf(
        NoteEntity(
            id = 1L,
            title = "Coffee Experiment",
            text = "Tried a new coffee recipe today. Added a hint of cinnamon and it turned out to be quite delightful.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 2L,
            title = "Book Club",
            text = "Had a virtual meeting with the book club. Discussed 'The Great Gatsby'. Interesting perspectives all around.",
            color = (0xFFEB7A53).toInt()
        ),
        NoteEntity(
            id = 3L,
            title = "New Sketch",
            text = "Made a sketch of the city skyline. The sunset colors were truly inspiring.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 4L,
            title = "Baking a Cake",
            text = "Baked a chocolate cake for a friend's birthday. It was a hit!",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 5L,
            title = "Stargazing",
            text = "Went stargazing last night. The sky was clear and I could see the constellations.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 6L,
            title = "Planting Flowers",
            text = "Spent the afternoon planting flowers. Can't wait to see them bloom!",
            color = (0xFFEB7A53).toInt()
        ),
        NoteEntity(
            id = 7L,
            title = "Yoga Class",
            text = "Took a yoga class today. It was challenging but felt great.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 8L,
            title = "Visit to the Museum",
            text = "Visited the art museum. The impressionist collection was my favorite.",
            color = (0xFFA8D672).toInt()
        ),
        NoteEntity(
            id = 9L,
            title = "Mountain Hike",
            text = "Went on a mountain hike. The view from the top was breathtaking.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 10L,
            title = "Learning Spanish",
            text = "Started learning Spanish today. It's challenging but I'm enjoying the process.",
            color = (0xFFA8D672).toInt()
        ),
        NoteEntity(
            id = 11L,
            title = "Gardening",
            text = "Did some gardening. The roses are blooming beautifully.",
            color = (0xFFA8D672).toInt()
        ),
        NoteEntity(
            id = 12L,
            title = "Cooking Experiment",
            text = "Experimented with some ingredients and ended up making a delicious pasta.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 13L,
            title = "Beach Day",
            text = "Spent the day at the beach. The weather was perfect.",
            color = (0xFFEB7A53).toInt()
        ),
        NoteEntity(
            id = 14L,
            title = "New Music",
            text = "Discovered a new band today. Their music is really uplifting.",
            color = (0xFFA8D672).toInt()
        ),
        NoteEntity(
            id = 15L,
            title = "Writing a Story",
            text = "Started writing a short story. It's a mystery thriller.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 16L,
            title = "Photography",
            text = "Took some amazing photos of the sunset today.",
            color = (0xFF98B7DB).toInt()
        ),
        NoteEntity(
            id = 17L,
            title = "Visit to the Zoo",
            text = "Went to the zoo. The pandas were the highlight of the day.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 18L,
            title = "Movie Night",
            text = "Had a movie night with friends. We watched a classic comedy.",
            color = (0xFFEB7A53).toInt()
        ),
        NoteEntity(
            id = 19L,
            title = "Picnic",
            text = "Had a picnic in the park. The sandwiches were delicious.",
            color = (0xFF98B7DB).toInt()
        ),
        NoteEntity(
            id = 20L,
            title = "Road Trip",
            text = "Went on a road trip with friends. The scenic route was absolutely breathtaking.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 21L,
            title = "Art Exhibition",
            text = "Visited an art exhibition. The contemporary pieces were thought-provoking.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 22L,
            title = "Charity Run",
            text = "Participated in a charity run. It felt great to contribute to a good cause.",
            color = (0xFF98B7DB).toInt()
        ),
        NoteEntity(
            id = 23L,
            title = "Pottery Class",
            text = "Took a pottery class. Made a vase that turned out quite well.",
            color = (0xFFEB7A53).toInt()
        ),
        NoteEntity(
            id = 24L,
            title = "Bird Watching",
            text = "Went bird watching in the woods. Saw a beautiful cardinal.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 25L,
            title = "Fishing Trip",
            text = "Went on a fishing trip. Caught a big trout.",
            color = (0xFFF6ECC9).toInt()
        ),
        NoteEntity(
            id = 26L,
            title = "Concert",
            text = "Went to a rock concert. The energy was incredible.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 27L,
            title = "Cooking Class",
            text = "Took a cooking class. Learned to make sushi.",
            color = (0xFFA8D672).toInt()
        ),
        NoteEntity(
            id = 28L,
            title = "Swimming",
            text = "Went swimming at the local pool. The water was refreshing.",
            color = (0xFF98B7DB).toInt()
        ),
        NoteEntity(
            id = 29L,
            title = "Camping",
            text = "Went camping in the mountains. The starry sky was a sight to behold.",
            color = (0xFFF7D44C).toInt()
        ),
        NoteEntity(
            id = 30L,
            title = "Morning Jog",
            text = "Went for a morning jog around the park. The fresh air and chirping birds made it a great start to the day.",
            color = (0xFF98B7DB).toInt()
        )
    )
    private val folders = mutableListOf<FolderEntity>()
    private val crossRefs = mutableListOf<NoteFolderCrossRef>()

    override fun getNotes(folderId: Long?): Flow<List<NoteEntity>> {
        return if (folderId == null) {
            flow { emit(notes) }
        } else {
            val noteIds = crossRefs.filter { it.folderId == folderId }.map { it.noteId }
            flow { emit(notes.filter { it.id in noteIds }) }
        }
    }

    override suspend fun getNoteById(noteId: Long): NoteEntity? {
        return notes.find { it.id == noteId }
    }

    override suspend fun addNote(note: NoteEntity, folderId: Long?) {
        val existingNote = notes.find { it.id == note.id }
        if (existingNote != null) {
            val index = notes.indexOf(existingNote)
            notes[index] = note
        } else {
            val noteId = (notes.maxByOrNull { it.id }?.id ?: 0) + 1
            notes.add(note.copy(id = noteId))
        }
    }

    override fun getFolders(): Flow<List<FolderEntity>> {
        return flow { emit(folders) }
    }

    override suspend fun addFolder(folder: FolderEntity) {
        val folderId = (folders.maxByOrNull { it.id }?.id ?: 0) + 1
        folders.add(folder.copy(id = folderId))
    }
}