import com.example.domain.Domain.repository.FriendRepository

class AddInFriendUseCase(private val addInFriendRepository: FriendRepository) {

    fun execute(email: String)
    {
        addInFriendRepository.setInFriend(email)
    }
}