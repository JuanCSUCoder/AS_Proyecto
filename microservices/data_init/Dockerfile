FROM node:22

WORKDIR /app

COPY . .

RUN rm .env
RUN npm install

CMD [ "sleep", "15", "&&", "npx", "prisma", "migrate", "dev", "--name", "init" ]
