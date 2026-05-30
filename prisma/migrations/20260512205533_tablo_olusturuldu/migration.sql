-- CreateTable
CREATE TABLE "User" (
    "id" INTEGER NOT NULL,
    "email" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "surname" TEXT NOT NULL,
    "password" TEXT NOT NULL,

    CONSTRAINT "User_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Teacher" (
    "Teacherid" INTEGER NOT NULL,
    "email" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "surname" TEXT NOT NULL,
    "password" TEXT NOT NULL,

    CONSTRAINT "Teacher_pkey" PRIMARY KEY ("Teacherid")
);

-- CreateTable
CREATE TABLE "timeTable" (
    "timeTableid" INTEGER NOT NULL,
    "teacherId" INTEGER NOT NULL,
    "day" TEXT NOT NULL,
    "startTime" TEXT NOT NULL,
    "endTime" TEXT NOT NULL,

    CONSTRAINT "timeTable_pkey" PRIMARY KEY ("timeTableid")
);

-- CreateTable
CREATE TABLE "Appointment" (
    "Appointmentid" INTEGER NOT NULL,
    "date" TIMESTAMP(3) NOT NULL,
    "studentId" INTEGER NOT NULL,
    "teacherId" INTEGER NOT NULL,
    "isonline" BOOLEAN NOT NULL,
    "old" BOOLEAN NOT NULL,
    "subject" TEXT NOT NULL,

    CONSTRAINT "Appointment_pkey" PRIMARY KEY ("Appointmentid")
);

-- CreateIndex
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Teacher_email_key" ON "Teacher"("email");

-- AddForeignKey
ALTER TABLE "timeTable" ADD CONSTRAINT "timeTable_teacherId_fkey" FOREIGN KEY ("teacherId") REFERENCES "Teacher"("Teacherid") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Appointment" ADD CONSTRAINT "Appointment_studentId_fkey" FOREIGN KEY ("studentId") REFERENCES "User"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Appointment" ADD CONSTRAINT "Appointment_teacherId_fkey" FOREIGN KEY ("teacherId") REFERENCES "Teacher"("Teacherid") ON DELETE RESTRICT ON UPDATE CASCADE;
