package sarmisdead.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        val exerciseList = ArrayList<ExerciseModel>()

        val pose1 = ExerciseModel(
            1,
            "Halfway Lift Pose",
            R.drawable.pose1,
            false,
            false
        )
        exerciseList.add(pose1)

        val pose2 = ExerciseModel(
            2,
            "Standing Foward Fold Pose",
            R.drawable.pose2,
            false,
            false
        )
        exerciseList.add(pose2)

        val pose3 = ExerciseModel(
            3,
            "Crane Pose",
            R.drawable.pose3,
            false,
            false
        )
        exerciseList.add(pose3)

        val pose4 = ExerciseModel(
            4,
            "Downward-Facing Dog Pose",
            R.drawable.pose4,
            false,
            false
        )
        exerciseList.add(pose4)

        val pose5 = ExerciseModel(
            5,
            "Bow Pose",
            R.drawable.pose5,
            false,
            false
        )
        exerciseList.add(pose5)

        val pose6 = ExerciseModel(
            6,
            "Crane Pose",
            R.drawable.pose6,
            false,
            false
        )
        exerciseList.add(pose6)

        val pose7 = ExerciseModel(
            7,
            "Warrior II Pose",
            R.drawable.pose7,
            false,
            false
        )
        exerciseList.add(pose7)

        val pose8 = ExerciseModel(
            8,
            "Camel Pose",
            R.drawable.pose8,
            false,
            false
        )
        exerciseList.add(pose8)

        return exerciseList

    }//End of ArrayList

} //End of Constants