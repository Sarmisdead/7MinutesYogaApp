package sarmisdead.a7minutesworkout

class ExerciseModel (

    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean

    ) {
    /* Getters */
    fun getId(): Int {

        return id

    } //End of getId

    fun getName(): String {

        return name

    } //End of getName

    fun getImage(): Int {

        return image

    } //End of getImage

    fun getIsCompleted(): Boolean {

        return isCompleted

    } //End of getIsCompleted

    fun getIsSelected(): Boolean {

        return isSelected

    } //End of getIsSelected

    // ----------------------------------------------------

    /* Setters */
    fun setId(id : Int){

        this.id = id

    } //End of setId

    fun setName(id : String){

        this.name = name

    } //End of setName

    fun setImage(image : Int){

        this.image = image

    } //End of setImage

    fun setIsCompleted(isCompleted : Boolean){

        this.isCompleted = isCompleted

    } //End of setIsCompleted

    fun setIsSelected(isSelected : Boolean){

        this.isSelected = isSelected

    } //End of setIsSelected

} //End of ExerciseModel
