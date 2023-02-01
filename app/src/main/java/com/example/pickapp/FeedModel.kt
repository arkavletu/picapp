import com.example.pickapp.dto.Reply

data class FeedModel(
    val reply: Reply = Reply(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false
    //val refreshing: Boolean = false,
)
